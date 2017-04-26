package org.gr.woc.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.CommonDataSource;

import org.gr.woc.biz.IUserInfBiz;
import org.gr.woc.biz.impl.UserInfBizImpl;
import org.gr.woc.po.Detail_Inf;
import org.gr.woc.po.User;
import org.gr.woc.vo.Friends;


/**
 * Servlet implementation class FindRealizer
 */
class UserNode{
	int userId;
	List<Integer>friendId;
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((friendId == null) ? 0 : friendId.hashCode());
		result = prime * result + userId;
		return result;
	}
	public UserNode() {
		super();
		// TODO Auto-generated constructor stub
		friendId=new ArrayList<Integer>();
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserNode other = (UserNode) obj;
		if (friendId == null) {
			if (other.friendId != null)
				return false;
		} else if (!friendId.equals(other.friendId))
			return false;
		if (userId != other.userId)
			return false;
		return true;
	}
}
public class FindRealizerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
	private IUserInfBiz biz;
	private List<UserNode>friendGraph;
    public FindRealizerServlet() {
        super();
        
    }

    public void findRealizer(int userId, List<Integer>toReturn, int nowdeep, int deep){
    	if(nowdeep==deep)
    	{
    		toReturn.add(userId);
    		return;
    	}
    	if(getUserNode(userId)==null)
    		return;
    	List<Integer>temp=getUserNode(userId).friendId;
    	for(int i=0;i<temp.size();i++){
    		findRealizer(temp.get(i), toReturn, nowdeep+1, deep);
    	}
    	
    }
    private UserNode getUserNode(int userId){
    	for(int i=0;i<this.friendGraph.size();i++){
    		if(this.friendGraph.get(i).userId==userId){
    			return this.friendGraph.get(i);
    		}
    	}
    	return null;
    }
    private void earaseReloop(List<Integer>toReturn, int userId){
    	List<Friends>friend=biz.searchFriendInformation(userId);
    	for(int i=toReturn.size()-1;i>=0;i--){
    		for(int j=0;j<friend.size();j++){
    			if(friend.get(j).getFriendId()==toReturn.get(i)){
    				toReturn.remove(i);
    				break;
    			}
    		}
    	}
    	for(int i=0;i<toReturn.size();i++){
    		if(toReturn.get(i)==userId)
    		{
    			toReturn.remove(i);
    			i--;
    		}
    	}
    	for(int i=0;i<toReturn.size();i++){
    		int time=1;
    		for(int j=0;j<toReturn.size();j++){
    			if(toReturn.get(i)==toReturn.get(j)){
    				if(time>1){
    					toReturn.remove(j);
    				}
    				time++;
    			}
    			
    		}
    	}
    }
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		biz=new UserInfBizImpl();
        // TODO Auto-generated constructor stub
        List<Integer>users=biz.seachAllUserId();
        friendGraph=new ArrayList<UserNode>();
        for(int i=0;i<users.size();i++){
        	List<Friends>friend=biz.searchFriendInformation(users.get(i));
        	UserNode node=new UserNode();
        	node.userId=users.get(i);
        	if(friend==null||friend.size()==0)
        		continue;
        	for(int j=0;j<friend.size();j++){
        		node.friendId.add(friend.get(j).getFriendId());
        	}
        	friendGraph.add(node);
        }
		HttpSession session=request.getSession();
		User user=(User) session.getAttribute("user");
		int userId=user.getUserId();
		List<Integer>commonFriendId=new ArrayList<Integer>();
		List<Detail_Inf>commonFriend=new ArrayList<Detail_Inf>();
		this.findRealizer(userId, commonFriendId, 0, 2);
		this.earaseReloop(commonFriendId, userId);
		for(int i=0;i<commonFriendId.size();i++){
			commonFriend.add(biz.searchInfById(commonFriendId.get(i)));
		}
		request.setAttribute("lstFriends", commonFriend);
		
		String u=request.getParameter("u");
		String url;
		if(u.equals("1"))url="jsp/forum/user_marif.jsp";
		else url="jsp/forum/user_forif.jsp";
		RequestDispatcher dispatcher=request.getRequestDispatcher(url);
		dispatcher.forward(request, response);
	}

}
