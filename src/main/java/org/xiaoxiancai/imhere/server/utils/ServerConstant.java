/* 
 **********************************************************************
 * Copyright (c) 2014, xianneng.lin@gmail.com All Rights Reserved. 
 **********************************************************************
 */
package org.xiaoxiancai.imhere.server.utils;

/**
 * 服务端常量
 * 
 * @author xiannenglin
 */
public class ServerConstant {

	/**
	 * 更新命令
	 */
	public static final String COMMAND_UPDATE = "update";

	/**
	 * 解码器:业务选择器
	 */
	public static final String DECODER_SELECTOR = "business-selector-decoder";

	/**
	 * 解码器:注册
	 */
	public static final String DECODER_REGISTER = "register-decoder";

	/**
	 * 处理器:注册
	 */
	public static final String HANDLER_REGISTER = "register-handler";

	/**
	 * 解码器:更新
	 */
	public static final String DECODER_UPDATE = "update-decoder";

	/**
	 * 解码器:定位
	 */
	public static final String DECODER_LOCATE = "locate-decoder";

	/**
	 * protobuf编码器
	 */
	public static final String ENCODER = "encoder";

	/**
	 * 处理器:业务逻辑分发
	 */
	public static final String HANDLER_DISPATCHER = "dispatcher-handler";
}
