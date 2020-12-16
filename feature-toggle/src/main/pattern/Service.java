package main.pattern;

import main.user.User;

// 生成欢迎信息接口
public interface Service {

	// 为用户传递欢迎信息
	String getWelcomeMessage(User user);
	
	// 返回要显示的欢迎消息是否为增强版本。
	boolean isEnhanced();
}
