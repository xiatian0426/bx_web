<%@ page language="java" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>关于我们</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<script src="${jsRoot}/jquery-1.11.1.min.js"></script>
	<link rel="stylesheet" href="${cssRoot}/main.css"/>
</head>
<style>

@font-face{
font-family:"Times New Roman";
}

@font-face{
font-family:"宋体";
}

@font-face{
font-family:"Wingdings";
}

@font-face{
font-family:"Calibri";
}

@font-face{
font-family:"微软雅黑";
}

@list l0:level1{
mso-level-number-format:decimal;
mso-level-suffix:tab;
mso-level-text:"%1.";
mso-level-tab-stop:15.6000pt;
mso-level-number-position:left;
margin-left:0.0000pt;
text-indent:0.0000pt;
font-family:'Times New Roman';}

p.MsoNormal{
mso-style-name:正文;
mso-style-parent:"";
margin:0pt;
margin-bottom:.0001pt;
mso-pagination:none;
text-align:justify;
text-justify:inter-ideograph;
font-family:Calibri;
mso-fareast-font-family:宋体;
mso-bidi-font-family:'Times New Roman';
font-size:10.5000pt;
mso-font-kerning:1.0000pt;
}

span.10{
font-family:'Times New Roman';
}

span.15{
font-family:'Times New Roman';
font-weight:bold;
}

span.msoIns{
mso-style-type:export-only;
mso-style-name:"";
text-decoration:underline;
text-underline:single;
color:blue;
}

span.msoDel{
mso-style-type:export-only;
mso-style-name:"";
text-decoration:line-through;
color:red;
}

table.MsoNormalTable{
mso-style-name:普通表格;
mso-style-parent:"";
mso-style-noshow:yes;
mso-tstyle-rowband-size:0;
mso-tstyle-colband-size:0;
mso-padding-alt:0.0000pt 5.4000pt 0.0000pt 5.4000pt;
mso-para-margin:0pt;
mso-para-margin-bottom:.0001pt;
mso-pagination:widow-orphan;
font-family:'Times New Roman';
font-size:10.0000pt;
mso-ansi-language:#0400;
mso-fareast-language:#0400;
mso-bidi-language:#0400;
}
@page{mso-page-border-surround-header:no;
	mso-page-border-surround-footer:no;}@page Section0{
margin-top:72.0000pt;
margin-bottom:72.0000pt;
margin-left:90.0000pt;
margin-right:90.0000pt;
size:595.3000pt 841.9000pt;
layout-grid:15.6000pt;
}
div.Section0{page:Section0;}
</style>
<jsp:include  page="../def/top.jsp"/>
<body style="tab-interval:21pt;text-justify-trim:punctuation;" ><!--StartFragment--><div class="Section0"  style="margin:0  auto; margin-top:30px; margin-bottom:30px;width: 700px;layout-grid:15.6000pt;" ><p class=MsoNormal  align=center  style="text-align:center;" ><b><span style="padding-bottom: 20px;mso-spacerun:'yes';font-family:宋体;mso-ascii-font-family:Calibri;mso-hansi-font-family:Calibri;mso-bidi-font-family:'Times New Roman';font-weight:bold;font-size:16.0000pt;mso-font-kerning:1.0000pt;" ><font face="宋体" >关于我们</font></span></b><b><span style="mso-spacerun:'yes';font-family:宋体;mso-ascii-font-family:Calibri;mso-hansi-font-family:Calibri;mso-bidi-font-family:'Times New Roman';font-weight:bold;font-size:26.0000pt;mso-font-kerning:1.0000pt;" ><o:p></o:p></span></b></p><p class=MsoNormal  style="margin-top:20px; text-indent:21.0000pt;mso-char-indent-count:2.0000;" ><span style="mso-spacerun:'yes';font-family:微软雅黑;color:rgb(51,51,51);letter-spacing:0.0000pt;font-weight:normal;text-transform:none;font-style:normal;font-size:10.5000pt;mso-font-kerning:1.0000pt;background:rgb(255,255,255);mso-shading:rgb(255,255,255);" ><font face="微软雅黑" >中项招标网</font></span><span style="mso-spacerun:'yes';font-family:微软雅黑;color:rgb(51,51,51);letter-spacing:0.0000pt;font-weight:normal;text-transform:none;font-style:normal;font-size:10.5000pt;mso-font-kerning:1.0000pt;background:rgb(255,255,255);mso-shading:rgb(255,255,255);" >(www.</span><span style="mso-spacerun:'yes';font-family:微软雅黑;color:rgb(51,51,51);letter-spacing:0.0000pt;font-weight:normal;text-transform:none;font-style:normal;font-size:10.5000pt;mso-font-kerning:1.0000pt;background:rgb(255,255,255);mso-shading:rgb(255,255,255);" >zhongxiangzhaobaio</span><span style="mso-spacerun:'yes';font-family:微软雅黑;color:rgb(51,51,51);letter-spacing:0.0000pt;font-weight:normal;text-transform:none;font-style:normal;font-size:10.5000pt;mso-font-kerning:1.0000pt;background:rgb(255,255,255);mso-shading:rgb(255,255,255);" >.</span><span style="mso-spacerun:'yes';font-family:微软雅黑;color:rgb(51,51,51);letter-spacing:0.0000pt;font-weight:normal;text-transform:none;font-style:normal;font-size:10.5000pt;mso-font-kerning:1.0000pt;background:rgb(255,255,255);mso-shading:rgb(255,255,255);" >com</span><span style="mso-spacerun:'yes';font-family:微软雅黑;color:rgb(51,51,51);letter-spacing:0.0000pt;font-weight:normal;text-transform:none;font-style:normal;font-size:10.5000pt;mso-font-kerning:1.0000pt;background:rgb(255,255,255);mso-shading:rgb(255,255,255);" >)，由成都</span><span style="mso-spacerun:'yes';font-family:微软雅黑;color:rgb(51,51,51);letter-spacing:0.0000pt;font-weight:normal;text-transform:none;font-style:normal;font-size:10.5000pt;mso-font-kerning:1.0000pt;background:rgb(255,255,255);mso-shading:rgb(255,255,255);" ><font face="微软雅黑" >鼎新启慧科技有限公司</font></span><span style="mso-spacerun:'yes';font-family:微软雅黑;color:rgb(51,51,51);letter-spacing:0.0000pt;font-weight:normal;text-transform:none;font-style:normal;font-size:10.5000pt;mso-font-kerning:1.0000pt;background:rgb(255,255,255);mso-shading:rgb(255,255,255);" ><font face="微软雅黑" >创办</font></span><span style="mso-spacerun:'yes';font-family:微软雅黑;color:rgb(51,51,51);letter-spacing:0.0000pt;font-weight:normal;text-transform:none;font-style:normal;font-size:10.5000pt;mso-font-kerning:1.0000pt;background:rgb(255,255,255);mso-shading:rgb(255,255,255);" ><font face="微软雅黑" >运营。</font></span><span style="mso-spacerun:'yes';font-family:微软雅黑;color:rgb(51,51,51);letter-spacing:0.0000pt;font-weight:normal;text-transform:none;font-style:normal;font-size:10.5000pt;mso-font-kerning:1.0000pt;background:rgb(255,255,255);mso-shading:rgb(255,255,255);" ><font face="微软雅黑" >在有关部门和各界专家的指导下</font></span><span style="mso-spacerun:'yes';font-family:微软雅黑;color:rgb(51,51,51);letter-spacing:0.0000pt;font-weight:normal;text-transform:none;font-style:normal;font-size:10.5000pt;mso-font-kerning:1.0000pt;background:rgb(255,255,255);mso-shading:rgb(255,255,255);" ><font face="微软雅黑" >，</font></span><span style="mso-spacerun:'yes';font-family:微软雅黑;color:rgb(51,51,51);letter-spacing:0.0000pt;font-weight:normal;text-transform:none;font-style:normal;font-size:10.5000pt;mso-font-kerning:1.0000pt;background:rgb(255,255,255);mso-shading:rgb(255,255,255);" ><font face="微软雅黑" >积极响应《中华人民共和国招标投标法》，为优化公共招投标市场而诞生！</font></span><span style="mso-spacerun:'yes';font-family:微软雅黑;color:rgb(51,51,51);letter-spacing:0.0000pt;font-weight:normal;text-transform:none;font-style:normal;font-size:10.5000pt;mso-font-kerning:1.0000pt;background:rgb(255,255,255);mso-shading:rgb(255,255,255);" >&#160;</span><span style="mso-spacerun:'yes';font-family:微软雅黑;color:rgb(51,51,51);letter-spacing:0.0000pt;font-weight:normal;text-transform:none;font-style:normal;font-size:10.5000pt;mso-font-kerning:1.0000pt;background:rgb(255,255,255);mso-shading:rgb(255,255,255);" ><font face="微软雅黑" >致力于为招投标</font></span><span style="mso-spacerun:'yes';font-family:微软雅黑;color:rgb(51,51,51);letter-spacing:0.0000pt;font-weight:normal;text-transform:none;font-style:normal;font-size:10.5000pt;mso-font-kerning:1.0000pt;background:rgb(255,255,255);mso-shading:rgb(255,255,255);" ><font face="微软雅黑" >及采购领域的企业提供精准的</font></span><span style="mso-spacerun:'yes';font-family:微软雅黑;color:rgb(51,51,51);letter-spacing:0.0000pt;font-weight:normal;text-transform:none;font-style:normal;font-size:10.5000pt;mso-font-kerning:1.0000pt;background:rgb(255,255,255);mso-shading:rgb(255,255,255);" ><font face="微软雅黑" >招标、采购、拟在建项目</font></span><span style="mso-spacerun:'yes';font-family:微软雅黑;color:rgb(51,51,51);letter-spacing:0.0000pt;font-weight:normal;text-transform:none;font-style:normal;font-size:10.5000pt;mso-font-kerning:1.0000pt;background:rgb(255,255,255);mso-shading:rgb(255,255,255);" ><font face="微软雅黑" >等信息以及专业的商务服务！</font></span><span style="mso-spacerun:'yes';font-family:微软雅黑;color:rgb(51,51,51);letter-spacing:0.0000pt;font-weight:normal;text-transform:none;font-style:normal;font-size:10.5000pt;mso-font-kerning:1.0000pt;background:rgb(255,255,255);mso-shading:rgb(255,255,255);" ><o:p></o:p></span></p><p class=MsoNormal  style="text-indent:21.0000pt;mso-char-indent-count:2.0000;" ><span style="mso-spacerun:'yes';font-family:微软雅黑;color:rgb(51,51,51);letter-spacing:0.0000pt;font-weight:normal;text-transform:none;font-style:normal;font-size:10.5000pt;mso-font-kerning:1.0000pt;background:rgb(255,255,255);mso-shading:rgb(255,255,255);" ><font face="微软雅黑" >中项招标网信息涉及全国</font></span><span style="mso-spacerun:'yes';font-family:微软雅黑;color:rgb(51,51,51);letter-spacing:0.0000pt;font-weight:normal;text-transform:none;font-style:normal;font-size:10.5000pt;mso-font-kerning:1.0000pt;background:rgb(255,255,255);mso-shading:rgb(255,255,255);" >31个省、直辖市，</span><span style="mso-spacerun:'yes';font-family:微软雅黑;color:rgb(51,51,51);letter-spacing:0.0000pt;font-weight:normal;text-transform:none;font-style:normal;font-size:10.5000pt;mso-font-kerning:1.0000pt;background:rgb(255,255,255);mso-shading:rgb(255,255,255);" ><font face="微软雅黑" >覆盖了大部分国家财政性投资项目和社会投资项目，同时与国内多家业主、招标公司、设计单位建立了合作关系，保证项目信息及时、准确性和全面性。</font></span><span style="mso-spacerun:'yes';font-family:微软雅黑;color:rgb(51,51,51);letter-spacing:0.0000pt;font-weight:normal;text-transform:none;font-style:normal;font-size:10.5000pt;mso-font-kerning:1.0000pt;background:rgb(255,255,255);mso-shading:rgb(255,255,255);" ><o:p></o:p></span></p><p class=MsoNormal  style="text-indent:21.0000pt;mso-char-indent-count:2.0000;" ><span style="mso-spacerun:'yes';font-family:微软雅黑;color:rgb(51,51,51);letter-spacing:0.0000pt;font-weight:normal;text-transform:none;font-style:normal;font-size:10.5000pt;mso-font-kerning:1.0000pt;background:rgb(255,255,255);mso-shading:rgb(255,255,255);" ><font face="微软雅黑" >中项招标网依托现代化的信息手段、先进的网络技术和专业的行业人才，大大的提升了招投标领域的企业工作效率，降低了成本。我们坚持以</font>&#8220;精准的信息和专业的服务&#8221;为企业根本，以客户为中心，不断改进提高，为企业提供更高质量的服务！</span><span style="mso-spacerun:'yes';font-family:微软雅黑;color:rgb(51,51,51);letter-spacing:0.0000pt;font-weight:normal;text-transform:none;font-style:normal;font-size:10.5000pt;mso-font-kerning:1.0000pt;background:rgb(255,255,255);mso-shading:rgb(255,255,255);" ><o:p></o:p></span></p><p class=MsoNormal  style="text-indent:21.0000pt;mso-char-indent-count:2.0000;mso-list:l0 level1 lfo1;" ><![if !supportLists]><span style="font-family:微软雅黑;color:rgb(51,51,51);letter-spacing:0.0000pt;font-weight:bold;text-transform:none;font-style:normal;font-size:10.5000pt;mso-font-kerning:1.0000pt;background:rgb(255,255,255);mso-shading:rgb(255,255,255);" ><span style='mso-list:Ignore;' >1.<span>&nbsp;</span></span></span><![endif]><b><span style="mso-spacerun:'yes';font-family:微软雅黑;color:rgb(51,51,51);letter-spacing:0.0000pt;font-weight:bold;text-transform:none;font-style:normal;font-size:10.5000pt;mso-font-kerning:1.0000pt;background:rgb(255,255,255);mso-shading:rgb(255,255,255);" ><font face="微软雅黑" >准确性和权威性：</font></span></b><span style="mso-spacerun:'yes';font-family:微软雅黑;color:rgb(51,51,51);letter-spacing:0.0000pt;font-weight:normal;text-transform:none;font-style:normal;font-size:10.5000pt;mso-font-kerning:1.0000pt;background:rgb(255,255,255);mso-shading:rgb(255,255,255);" ><font face="微软雅黑" >以跨部委的政府资源为基础，与全国各行业协会及众多的招标代理机构和业主单位建立坚实的合作，获取各个行业和地区最为全面和准确的招标、采购及项目信息。</font></span><span style="mso-spacerun:'yes';font-family:微软雅黑;color:rgb(51,51,51);letter-spacing:0.0000pt;font-weight:normal;text-transform:none;font-style:normal;font-size:10.5000pt;mso-font-kerning:1.0000pt;background:rgb(255,255,255);mso-shading:rgb(255,255,255);" ><o:p></o:p></span></p><p class=MsoNormal  style="text-indent:21.0000pt;mso-char-indent-count:2.0000;mso-list:l0 level1 lfo1;" ><![if !supportLists]><span style="font-family:微软雅黑;color:rgb(51,51,51);letter-spacing:0.0000pt;font-weight:bold;text-transform:none;font-style:normal;font-size:10.5000pt;mso-font-kerning:1.0000pt;background:rgb(255,255,255);mso-shading:rgb(255,255,255);" ><span style='mso-list:Ignore;' >2.<span>&nbsp;</span></span></span><![endif]><b><span style="mso-spacerun:'yes';font-family:微软雅黑;color:rgb(51,51,51);letter-spacing:0.0000pt;font-weight:bold;text-transform:none;font-style:normal;font-size:10.5000pt;mso-font-kerning:1.0000pt;background:rgb(255,255,255);mso-shading:rgb(255,255,255);" ><font face="微软雅黑" >信息快捷和准确：</font></span></b><span style="mso-spacerun:'yes';font-family:微软雅黑;color:rgb(51,51,51);letter-spacing:0.0000pt;font-weight:normal;text-transform:none;font-style:normal;font-size:10.5000pt;mso-font-kerning:1.0000pt;background:rgb(255,255,255);mso-shading:rgb(255,255,255);" >VIP独家项目、拟在建项目、与招标</span><span style="mso-spacerun:'yes';font-family:微软雅黑;color:rgb(51,51,51);letter-spacing:0.0000pt;font-weight:normal;text-transform:none;font-style:normal;font-size:10.5000pt;mso-font-kerning:1.0000pt;background:rgb(255,255,255);mso-shading:rgb(255,255,255);" ><font face="微软雅黑" >信息</font></span><span style="mso-spacerun:'yes';font-family:微软雅黑;color:rgb(51,51,51);letter-spacing:0.0000pt;font-weight:normal;text-transform:none;font-style:normal;font-size:10.5000pt;mso-font-kerning:1.0000pt;background:rgb(255,255,255);mso-shading:rgb(255,255,255);" ><font face="微软雅黑" >相结合，使公司在第一时间得到您关注的信息，增长公司的中标率和业务业绩，使公司能更全面了解行业内的动态。</font></span><span style="mso-spacerun:'yes';font-family:微软雅黑;color:rgb(51,51,51);letter-spacing:0.0000pt;font-weight:normal;text-transform:none;font-style:normal;font-size:10.5000pt;mso-font-kerning:1.0000pt;background:rgb(255,255,255);mso-shading:rgb(255,255,255);" ><o:p></o:p></span></p><p class=MsoNormal  style="text-indent:21.0000pt;mso-char-indent-count:2.0000;mso-list:l0 level1 lfo1;" ><![if !supportLists]><span style="font-family:微软雅黑;color:rgb(51,51,51);letter-spacing:0.0000pt;font-weight:bold;text-transform:none;font-style:normal;font-size:10.5000pt;mso-font-kerning:1.0000pt;background:rgb(255,255,255);mso-shading:rgb(255,255,255);" ><span style='mso-list:Ignore;' >3.<span>&nbsp;</span></span></span><![endif]><b><span style="mso-spacerun:'yes';font-family:微软雅黑;color:rgb(51,51,51);letter-spacing:0.0000pt;font-weight:bold;text-transform:none;font-style:normal;font-size:10.5000pt;mso-font-kerning:1.0000pt;background:rgb(255,255,255);mso-shading:rgb(255,255,255);" ><font face="微软雅黑" >广泛的信息覆盖面：</font></span></b><span style="mso-spacerun:'yes';font-family:微软雅黑;color:rgb(51,51,51);letter-spacing:0.0000pt;font-weight:normal;text-transform:none;font-style:normal;font-size:10.5000pt;mso-font-kerning:1.0000pt;background:rgb(255,255,255);mso-shading:rgb(255,255,255);" ><font face="微软雅黑" >全面覆盖</font></span><span style="mso-spacerun:'yes';font-family:微软雅黑;color:rgb(51,51,51);letter-spacing:0.0000pt;font-weight:normal;text-transform:none;font-style:normal;font-size:10.5000pt;mso-font-kerning:1.0000pt;background:rgb(255,255,255);mso-shading:rgb(255,255,255);" ><font face="微软雅黑" >国内各个</font></span><span style="mso-spacerun:'yes';font-family:微软雅黑;color:rgb(51,51,51);letter-spacing:0.0000pt;font-weight:normal;text-transform:none;font-style:normal;font-size:10.5000pt;mso-font-kerning:1.0000pt;background:rgb(255,255,255);mso-shading:rgb(255,255,255);" ><font face="微软雅黑" >地区</font></span><span style="mso-spacerun:'yes';font-family:微软雅黑;color:rgb(51,51,51);letter-spacing:0.0000pt;font-weight:normal;text-transform:none;font-style:normal;font-size:10.5000pt;mso-font-kerning:1.0000pt;background:rgb(255,255,255);mso-shading:rgb(255,255,255);" ><font face="微软雅黑" >、</font></span><span style="mso-spacerun:'yes';font-family:微软雅黑;color:rgb(51,51,51);letter-spacing:0.0000pt;font-weight:normal;text-transform:none;font-style:normal;font-size:10.5000pt;mso-font-kerning:1.0000pt;background:rgb(255,255,255);mso-shading:rgb(255,255,255);" ><font face="微软雅黑" >针对性的各个行业，让您掌握更多商机和信息，开拓业务！</font></span><b><span style="mso-spacerun:'yes';font-family:微软雅黑;color:rgb(51,51,51);letter-spacing:0.0000pt;font-weight:bold;text-transform:none;font-style:normal;font-size:10.5000pt;mso-font-kerning:1.0000pt;background:rgb(255,255,255);mso-shading:rgb(255,255,255);" ><o:p></o:p></span></b></p><p class=MsoNormal  style="text-indent:21.0000pt;mso-char-indent-count:2.0000;mso-list:l0 level1 lfo1;" ><![if !supportLists]><span style="font-family:微软雅黑;color:rgb(51,51,51);letter-spacing:0.0000pt;font-weight:bold;text-transform:none;font-style:normal;font-size:10.5000pt;mso-font-kerning:1.0000pt;background:rgb(255,255,255);mso-shading:rgb(255,255,255);" ><span style='mso-list:Ignore;' >4.<span>&nbsp;</span></span></span><![endif]><b><span style="mso-spacerun:'yes';font-family:微软雅黑;color:rgb(51,51,51);letter-spacing:0.0000pt;font-weight:bold;text-transform:none;font-style:normal;font-size:10.5000pt;mso-font-kerning:1.0000pt;background:rgb(255,255,255);mso-shading:rgb(255,255,255);" ><font face="微软雅黑" >专业的团队：</font></span></b><span style="mso-spacerun:'yes';font-family:微软雅黑;color:rgb(51,51,51);letter-spacing:0.0000pt;font-weight:normal;text-transform:none;font-style:normal;font-size:10.5000pt;mso-font-kerning:1.0000pt;background:rgb(255,255,255);mso-shading:rgb(255,255,255);" ><font face="微软雅黑" >会员服务的主要人员都具有一定的行业专业知识储备，可以高效的将信息提供给企业，提升效率，同时以专业的角度为企业带来深度的一系列服务！</font></span><b><span style="mso-spacerun:'yes';font-family:微软雅黑;color:rgb(51,51,51);letter-spacing:0.0000pt;font-weight:bold;text-transform:none;font-style:normal;font-size:10.5000pt;mso-font-kerning:1.0000pt;background:rgb(255,255,255);mso-shading:rgb(255,255,255);" ><o:p></o:p></span></b></p><p class=MsoNormal  style="text-indent:21.0000pt;mso-char-indent-count:2.0000;mso-list:l0 level1 lfo1;" ><![if !supportLists]><span style="font-family:微软雅黑;color:rgb(51,51,51);letter-spacing:0.0000pt;font-weight:bold;text-transform:none;font-style:normal;font-size:10.5000pt;mso-font-kerning:1.0000pt;background:rgb(255,255,255);mso-shading:rgb(255,255,255);" ><span style='mso-list:Ignore;' >5.<span>&nbsp;</span></span></span><![endif]><b><span style="mso-spacerun:'yes';font-family:微软雅黑;color:rgb(51,51,51);letter-spacing:0.0000pt;font-weight:bold;text-transform:none;font-style:normal;font-size:10.5000pt;mso-font-kerning:1.0000pt;background:rgb(255,255,255);mso-shading:rgb(255,255,255);" ><font face="微软雅黑" >个性化的服务：</font></span></b><span style="mso-spacerun:'yes';font-family:微软雅黑;color:rgb(51,51,51);letter-spacing:0.0000pt;font-weight:normal;text-transform:none;font-style:normal;font-size:10.5000pt;mso-font-kerning:1.0000pt;background:rgb(255,255,255);mso-shading:rgb(255,255,255);" ><font face="微软雅黑" >中项招标网会根据客户所需进行针对性的定制和匹配，按照客户要求及时的以</font></span><span style="mso-spacerun:'yes';font-family:微软雅黑;color:rgb(51,51,51);letter-spacing:0.0000pt;font-weight:normal;text-transform:none;font-style:normal;font-size:10.5000pt;mso-font-kerning:1.0000pt;background:rgb(255,255,255);mso-shading:rgb(255,255,255);" ><font face="微软雅黑" >电话、邮件、</font></span><span style="mso-spacerun:'yes';font-family:微软雅黑;color:rgb(51,51,51);letter-spacing:0.0000pt;font-weight:normal;text-transform:none;font-style:normal;font-size:10.5000pt;mso-font-kerning:1.0000pt;background:rgb(255,255,255);mso-shading:rgb(255,255,255);" ><font face="微软雅黑" >短信、微信</font></span><span style="mso-spacerun:'yes';font-family:微软雅黑;color:rgb(51,51,51);letter-spacing:0.0000pt;font-weight:normal;text-transform:none;font-style:normal;font-size:10.5000pt;mso-font-kerning:1.0000pt;background:rgb(255,255,255);mso-shading:rgb(255,255,255);" ><font face="微软雅黑" >等方式通知您，有效地帮助您避免因工作繁忙或其他原因没有及时得到消息</font></span><span style="mso-spacerun:'yes';font-family:微软雅黑;color:rgb(51,51,51);letter-spacing:0.0000pt;font-weight:normal;text-transform:none;font-style:normal;font-size:10.5000pt;mso-font-kerning:1.0000pt;background:rgb(255,255,255);mso-shading:rgb(255,255,255);" ><font face="微软雅黑" >！</font></span><b><span style="mso-spacerun:'yes';font-family:微软雅黑;color:rgb(51,51,51);letter-spacing:0.0000pt;font-weight:bold;text-transform:none;font-style:normal;font-size:10.5000pt;mso-font-kerning:1.0000pt;background:rgb(255,255,255);mso-shading:rgb(255,255,255);" ><o:p></o:p></span></b></p></div>
<jsp:include  page="../def/footer.jsp"/>
</body>
</html>