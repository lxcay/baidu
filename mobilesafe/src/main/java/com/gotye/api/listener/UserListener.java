package com.gotye.api.listener;

import java.util.List;

import com.gotye.api.GotyeStatusCode;
import com.gotye.api.GotyeUser;
/**
 * 用户相关操作监听
 *
 */
public interface UserListener  extends GotyeListener{
	/**
	 * 获取用户信息回调
	 * @param code 状态码 参见 {@link com.gotye.api.GotyeStatusCode}
	 * @param user 用户信息
	 */
	void onGetUserDetail(int code, GotyeUser user);

	/**
	 * 修改用户回调
	 * @param code 状态码 参见 {@link com.gotye.api.GotyeStatusCode}
	 * @param user 被修改的用户对象
	 */
	void onModifyUserInfo(int code, GotyeUser user);

	/**
	 * 搜索用户回调
	 * @param code 状态码 参见 {@link com.gotye.api.GotyeStatusCode}
	 * @param mList 结果集合
	 * @param pagerIndex 页码
	 */
	void onSearchUserList(int code, List<GotyeUser> mList, List<GotyeUser> curPageList, int pagerIndex);

	/**
	 * 添加好友回调
	 * @param code 状态码 参见 {@link com.gotye.api.GotyeStatusCode}
	 * @param user 被添加的好友
	 */
	void onAddFriend(int code, GotyeUser user);

	/**
	 * 获取好友列表回调
	 * @param code 状态码 参见 {@link com.gotye.api.GotyeStatusCode}
	 * @param mList 好友列表
	 */
	void onGetFriendList(int code, List<GotyeUser> mList);

	/**
	 * 添加黑名单回调
	 * @param code 状态码 参见 {@link com.gotye.api.GotyeStatusCode}
	 * @param user 被添加黑名单用户
	 */
	void onAddBlocked(int code, GotyeUser user);

	/**
	 * 删除好友回调
	 * @param code 状态码 参见 {@link com.gotye.api.GotyeStatusCode}
	 * @param user 被删除的好友
	 */
	void onRemoveFriend(int code, GotyeUser user);
	
	/**
	 * 删除的黑名单回调
	 * @param code 状态码 参见 {@link com.gotye.api.GotyeStatusCode}
	 * @param user 被删除的黑名单用户
	 */

	void onRemoveBlocked(int code, GotyeUser user);

	/**
	 * 获取黑名单列表回调
	 * @param code 状态码 参见 {@link com.gotye.api.GotyeStatusCode}
	 * @param mList 黑名单列表
	 */
	void onGetBlockedList(int code, List<GotyeUser> mList);

	/**
	 * 登陆后返回登录用户信息回调
	 * @param code 状态码 参见 {@link com.gotye.api.GotyeStatusCode}
	 * @param user 登陆用户信息
	 */
	void onGetProfile(int code, GotyeUser user);
	
	/**
	 * 获取当前客服回调
	 * @param code 状态码 参见 {@link com.gotye.api.GotyeStatusCode}
	 * @param cs 客服对象
	 * @param onlineStatus 在线状态
	 * @param welcomeMsg 欢迎语
	 */
	void onGetCustomerService(int code, GotyeUser cs, int onlineStatus,
                              String welcomeMsg);
}
