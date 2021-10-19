<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Cache-Control" content="max-age=300" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>我的信息 - 电子商城</title>
<meta name="Keywords" content="java,淘淘java" />
<meta name="description" content="在淘淘中找到了29910件java的类似商品，其中包含了“图书”，“电子书”，“教育音像”，“骑行运动”等类型的java的商品。" />
<link rel="stylesheet" type="text/css" href="/css/base.css" media="all" />
<link rel="stylesheet" type="text/css" href="/css/myjd.common.css" media="all" />
<link rel="stylesheet" type="text/css" href="/css/myjd.info.css" media="all" />
<script type="text/javascript" src="/js/jquery-1.6.4.js"></script>

</head>
<body>
<script type="text/javascript" src="/js/base-2011.js" charset="utf-8"></script>
<!-- header start -->
<jsp:include page="commons/header.jsp" />
<!-- header end -->

<div id="container">
<div class="w">
	
<div id="main">
    <div class="g-0">
        <div id="content" class="c-3-5">
            
			<div class="mod-main">
                        <div class="mt">
                            <ul class="extra-l">
                                <li class="fore-1"><a class="curr" href="http://i.jd.com/user/info">基本信息</a></li>
                                
                                
                            </ul>
                        </div>
                        <div class="mc">
                            <div class="user-set userset-lcol">
                                <div class="form">
                                    <div class="item">
                                        <span class="label"><em>*</em>昵称：</span>
                                        <div class="fl">
                                            <input type="text" class="itxt" maxlength="20" id="nickName" name="userVo.nickName" value="${tbUser.username}"/>
                                            <div class="clr"></div><div class="prompt-06"><span id="nickName_msg"></span></div>
                                        </div>
                                    </div>
                                    
                                    
									<div class="item">
									<span class="label">邮箱：</span>
									<div class="fl">
																				<div>
											<strong>    ${tbUser.email }
</strong><a target="_blank" class="ml5 ftx05" href="http://localhost:8084/page/update">修改</a><span class="ftx-03">&nbsp;&nbsp;&nbsp;已验证</span>
										</div>
																			</div>

									<div class="clr"></div>
								</div>
								<div class="item">
									<span class="label">手机：</span>
									<div class="fl">
																				<div>
											<strong>    ${tbUser.phone}
</strong><a target="_blank" class="ml5 ftx05" href="http://localhost:8084/page/update">修改</a><span class="ftx-03">&nbsp;&nbsp;&nbsp;已验证</span>
										</div>
																			</div>

									<div class="clr"></div>
								</div>
								<div class="item">
									<span class="label">密码：</span>
									<div class="fl">
																				<div>
											<strong>    *****
</strong><a target="_blank" class="ml5 ftx05" href="http://localhost:8084/page/update">修改</a><span class="ftx-03">&nbsp;&nbsp;&nbsp;已验证</span>
										</div>
																			</div>

									<div class="clr"></div>
								</div>
									
                                   
                                    
                                </div>
                            </div>
                            <div id="user-info">
                                <div class="u-pic">
                                    <img alt="用户头像" src="/images/defaultImgs/1.jpg">
                                    <div class="mask"></div>
                                    <div class="face-link-box"></div>
                                    <a href="http://i.jd.com/user/userinfo/showImg.html" class="face-link">修改头像</a>
                                </div>
                                <div class="info-m">
									<div><b>用户名：${tbUser.username}</b></div>
                                    <div class="u-level">
										<span class="rank r4">
											<s></s><a href="http://usergrade.jd.com/user/grade" target="_blank">金牌会员</a>
										</span>
                                    </div>
                                    <div class="shop-level">购物行为评级：<span><a target="_blank" href="http://help.jd.com/help/question-57.html#help2173">
										<s id="userCredit" class="rank-sh rank-sh01 rank-sh02"></s></a></span></div>
                                    <div>会员类型：个人用户</div>
                                </div>s
                            </div>
							<div class="fr ac" style="width:280px;">
								  注：修改手机和邮箱请到<a class="ml5 ftx05" href="#user-password-update-page">账户安全</a>
							</div>
                            <div class="clr"></div>
							
                        </div>
                    </div>            
        </div>
    </div>
    <div id="left" class="g-3-5 c-0">
        <!--js 加载异步加载的左侧菜单 -->
        <div id="menu"><h3>我的交易</h3><dl class="fore1"><dt><a target="_blank" clstag="homepage|keycount|home2013|hdd" id="_MYJD_ordercenter" href="http://localhost:8082/user/order-info.html" class="curr">我的订单</a></dt></dl><h3>我的信息</h3><dl class="fore1"><dt><a target="_blank" clstag="homepage|keycount|home2013|hdd" id="_MYJD_ordercenter" href="http://localhost:8082/user/user-info.html" class="curr">个人信息</a></dt></dl></div>
    <span class="clr"></span>
</div>
</div>
</div>

<!-- footer start -->
<jsp:include page="commons/footer.jsp" />
<!-- footer end -->
</body>
</html>