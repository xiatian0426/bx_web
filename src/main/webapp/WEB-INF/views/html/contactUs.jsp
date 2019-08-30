<%@ page language="java" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>联系我们</title>
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

h3{
mso-style-name:"标题 3";
mso-style-noshow:yes;
mso-style-next:正文;
margin-top:5.0000pt;
margin-bottom:5.0000pt;
mso-margin-top-alt:auto;
mso-margin-bottom-alt:auto;
mso-pagination:none;
text-align:left;
font-family:宋体;
font-weight:bold;
font-size:13.5000pt;
}

span.10{
font-family:'Times New Roman';
}

span.15{
font-family:'Times New Roman';
color:rgb(0,0,255);
text-decoration:underline;
text-underline:single;
}

p.p{
mso-style-name:"普通\(网站\)";
margin-top:5.0000pt;
margin-right:0.0000pt;
margin-bottom:5.0000pt;
margin-left:0.0000pt;
mso-margin-top-alt:auto;
mso-margin-bottom-alt:auto;
mso-pagination:none;
text-align:left;
font-family:Calibri;
mso-fareast-font-family:宋体;
mso-bidi-font-family:'Times New Roman';
font-size:12.0000pt;
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
<body style="tab-interval:21pt;text-justify-trim:punctuation;" ><!--StartFragment--><div class="Section0"  style="layout-grid:15.6000pt;margin:0  auto; margin-bottom:30px;width: 800px;" ><h3 style="margin-top:0.0000pt;margin-right:0.0000pt;margin-bottom:0.0000pt;margin-left:0.0000pt;text-indent:0.0000pt;padding:0pt 0pt 0pt 0pt ;mso-pagination:widow-orphan;background:rgb(255,255,255);" ><b><span style="mso-spacerun:'yes';font-family:微软雅黑;color:rgb(51,51,51);letter-spacing:0.0000pt;font-weight:bold;text-transform:none;font-style:normal;font-size:13.5000pt;mso-font-kerning:0.0000pt;background:rgb(255,255,255);mso-shading:rgb(255,255,255);" ><font face="微软雅黑" >联系我们</font></span></b><b><span style="mso-spacerun:'yes';font-family:微软雅黑;color:rgb(51,51,51);letter-spacing:0.0000pt;font-weight:bold;text-transform:none;font-style:normal;font-size:13.5000pt;mso-font-kerning:0.0000pt;background:rgb(255,255,255);mso-shading:rgb(255,255,255);" ><o:p></o:p></span></b></h3><p class=MsoNormal ><span style="mso-spacerun:'yes';font-family:Calibri;mso-fareast-font-family:宋体;mso-bidi-font-family:'Times New Roman';font-size:10.5000pt;mso-font-kerning:1.0000pt;" ><o:p>&nbsp;</o:p></span></p><p class=MsoNormal ><b><span style="mso-spacerun:'yes';font-family:宋体;mso-ascii-font-family:Calibri;mso-hansi-font-family:Calibri;mso-bidi-font-family:'Times New Roman';font-weight:bold;font-size:10.5000pt;mso-font-kerning:1.0000pt;" ><font face="宋体" >中项招标网</font></span></b><span style="mso-spacerun:'yes';font-family:宋体;mso-ascii-font-family:Calibri;mso-hansi-font-family:Calibri;mso-bidi-font-family:'Times New Roman';font-size:10.5000pt;mso-font-kerning:1.0000pt;" ><o:p></o:p></span></p><p class=p  style="margin-top:0.0000pt;margin-right:0.0000pt;margin-bottom:0.0000pt;margin-left:0.0000pt;text-indent:21.0000pt;padding:0pt 0pt 0pt 0pt ;mso-pagination:widow-orphan;line-height:22.5000pt;background:rgb(255,255,255);" ><b><span style="mso-spacerun:'yes';font-family:微软雅黑;color:rgb(153,0,0);letter-spacing:0.0000pt;font-weight:bold;text-transform:none;font-style:normal;font-size:10.5000pt;mso-font-kerning:0.0000pt;background:rgb(255,255,255);mso-shading:rgb(255,255,255);" ><font face="微软雅黑" >联系电话：</font></span></b><span style="mso-spacerun:'yes';font-family:微软雅黑;color:rgb(51,51,51);letter-spacing:0.0000pt;font-weight:normal;text-transform:none;font-style:normal;font-size:10.5000pt;mso-font-kerning:0.0000pt;background:rgb(255,255,255);mso-shading:rgb(255,255,255);" >&#160;</span><span style="mso-spacerun:'yes';font-family:微软雅黑;color:rgb(51,51,51);letter-spacing:0.0000pt;font-weight:normal;text-transform:none;font-style:normal;font-size:10.5000pt;mso-font-kerning:0.0000pt;background:rgb(255,255,255);mso-shading:rgb(255,255,255);" >028-81711663</span><span style="mso-spacerun:'yes';font-family:微软雅黑;color:rgb(51,51,51);letter-spacing:0.0000pt;font-weight:normal;text-transform:none;font-style:normal;font-size:10.5000pt;mso-font-kerning:0.0000pt;" ><o:p></o:p></span></p><p class=p  style="margin-top:0.0000pt;margin-right:0.0000pt;margin-bottom:0.0000pt;margin-left:0.0000pt;text-indent:21.0000pt;padding:0pt 0pt 0pt 0pt ;mso-pagination:widow-orphan;line-height:22.5000pt;background:rgb(255,255,255);" ><b><span style="mso-spacerun:'yes';font-family:微软雅黑;color:rgb(153,0,0);letter-spacing:0.0000pt;font-weight:bold;text-transform:none;font-style:normal;font-size:10.5000pt;mso-font-kerning:0.0000pt;background:rgb(255,255,255);mso-shading:rgb(255,255,255);" ><font face="微软雅黑" >邮政编码：</font></span></b><span style="mso-spacerun:'yes';font-family:微软雅黑;color:rgb(51,51,51);letter-spacing:0.0000pt;font-weight:normal;text-transform:none;font-style:normal;font-size:10.5000pt;mso-font-kerning:0.0000pt;background:rgb(255,255,255);mso-shading:rgb(255,255,255);" >&#160;610041</span><span style="mso-spacerun:'yes';font-family:微软雅黑;color:rgb(51,51,51);letter-spacing:0.0000pt;font-weight:normal;text-transform:none;font-style:normal;font-size:10.5000pt;mso-font-kerning:0.0000pt;background:rgb(255,255,255);mso-shading:rgb(255,255,255);" ><o:p></o:p></span></p><p class=p  style="margin-top:0.0000pt;margin-right:0.0000pt;margin-bottom:0.0000pt;margin-left:0.0000pt;text-indent:21.0000pt;padding:0pt 0pt 0pt 0pt ;mso-pagination:widow-orphan;line-height:22.5000pt;background:rgb(255,255,255);" ><b><span style="mso-spacerun:'yes';font-family:微软雅黑;color:rgb(153,0,0);letter-spacing:0.0000pt;font-weight:bold;text-transform:none;font-style:normal;font-size:10.5000pt;mso-font-kerning:0.0000pt;background:rgb(255,255,255);mso-shading:rgb(255,255,255);" ><font face="微软雅黑" >邮</font></span></b><b><span style="mso-spacerun:'yes';font-family:微软雅黑;color:rgb(153,0,0);letter-spacing:0.0000pt;font-weight:bold;text-transform:none;font-style:normal;font-size:10.5000pt;mso-font-kerning:0.0000pt;background:rgb(255,255,255);mso-shading:rgb(255,255,255);" >&nbsp;&nbsp;&nbsp;&nbsp;</span></b><b><span style="mso-spacerun:'yes';font-family:微软雅黑;color:rgb(153,0,0);letter-spacing:0.0000pt;font-weight:bold;text-transform:none;font-style:normal;font-size:10.5000pt;mso-font-kerning:0.0000pt;background:rgb(255,255,255);mso-shading:rgb(255,255,255);" >&nbsp;&nbsp;&nbsp;<font face="微软雅黑" >箱：</font></span></b><b><span style="mso-spacerun:'yes';font-family:微软雅黑;color:rgb(153,0,0);letter-spacing:0.0000pt;font-weight:bold;text-transform:none;font-style:normal;font-size:10.5000pt;mso-font-kerning:0.0000pt;background:rgb(255,255,255);mso-shading:rgb(255,255,255);" >&nbsp;</span></b><span><a href="mailto:dingxinqihui@163.com" ><b><u><span class="15"  style="mso-spacerun:'yes';font-family:微软雅黑;color:rgb(0,0,255);letter-spacing:0.0000pt;font-weight:bold;text-transform:none;text-decoration:underline;text-underline:single;font-style:normal;font-size:10.5000pt;background:rgb(255,255,255);mso-shading:rgb(255,255,255);" >guoruihongxin@163.com</span></u></b></a></span><b><span style="mso-spacerun:'yes';font-family:微软雅黑;color:rgb(153,0,0);letter-spacing:0.0000pt;font-weight:bold;text-transform:none;font-style:normal;font-size:10.5000pt;mso-font-kerning:0.0000pt;background:rgb(255,255,255);mso-shading:rgb(255,255,255);" ><o:p></o:p></span></b></p><p class=p  style="margin-top:0.0000pt;margin-right:0.0000pt;margin-bottom:0.0000pt;margin-left:0.0000pt;text-indent:21.0000pt;padding:0pt 0pt 0pt 0pt ;mso-pagination:widow-orphan;line-height:22.5000pt;background:rgb(255,255,255);" ><b><span style="mso-spacerun:'yes';font-family:微软雅黑;color:rgb(153,0,0);letter-spacing:0.0000pt;font-weight:bold;text-transform:none;font-style:normal;font-size:10.5000pt;mso-font-kerning:0.0000pt;background:rgb(255,255,255);mso-shading:rgb(255,255,255);" ><font face="微软雅黑" >公司地址：</font></span></b><span style="mso-spacerun:'yes';font-family:微软雅黑;color:rgb(51,51,51);letter-spacing:0.0000pt;font-weight:normal;text-transform:none;font-style:normal;font-size:10.5000pt;mso-font-kerning:0.0000pt;background:rgb(255,255,255);mso-shading:rgb(255,255,255);" >&#160;中国（四川）</span><span style="mso-spacerun:'yes';font-family:微软雅黑;color:rgb(51,51,51);letter-spacing:0.0000pt;font-weight:normal;text-transform:none;font-style:normal;font-size:10.5000pt;mso-font-kerning:0.0000pt;background:rgb(255,255,255);mso-shading:rgb(255,255,255);" ><font face="微软雅黑" >自由贸易试验区成都高新区剑南大道中段1537号2栋8层2号</font></span><span style="mso-spacerun:'yes';font-family:微软雅黑;color:rgb(51,51,51);letter-spacing:0.0000pt;font-weight:normal;text-transform:none;font-style:normal;font-size:10.5000pt;mso-font-kerning:0.0000pt;" ><o:p></o:p></span></p><p class=p  style="margin-top:0.0000pt;margin-right:0.0000pt;margin-bottom:0.0000pt;margin-left:0.0000pt;text-indent:21.0000pt;padding:0pt 0pt 0pt 0pt ;mso-pagination:widow-orphan;line-height:22.5000pt;background:rgb(255,255,255);" ><b><span style="mso-spacerun:'yes';font-family:微软雅黑;color:rgb(153,0,0);letter-spacing:0.0000pt;font-weight:bold;text-transform:none;font-style:normal;font-size:10.5000pt;mso-font-kerning:0.0000pt;background:rgb(255,255,255);mso-shading:rgb(255,255,255);" ><o:p>&nbsp;</o:p></span></b></p><p class=p  style="margin-top:0.0000pt;margin-right:0.0000pt;margin-bottom:0.0000pt;margin-left:0.0000pt;text-indent:21.0000pt;padding:0pt 0pt 0pt 0pt ;mso-pagination:widow-orphan;line-height:22.5000pt;background:rgb(255,255,255);" ><b><span style="mso-spacerun:'yes';font-family:微软雅黑;color:rgb(153,0,0);letter-spacing:0.0000pt;font-weight:bold;text-transform:none;font-style:normal;font-size:10.5000pt;mso-font-kerning:0.0000pt;background:rgb(255,255,255);mso-shading:rgb(255,255,255);" ><o:p>&nbsp;</o:p></span></b></p></div><!--EndFragment--></body></html>
<jsp:include  page="../def/footer.jsp"/>
</body>
</html>