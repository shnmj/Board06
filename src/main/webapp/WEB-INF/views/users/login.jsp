<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="icon" type="image/png" href="/img/favicon.png" />
<link rel="stylesheet"  href="/css/common.css" />
<style>
   input:not(input[type=submit]) { width:100%; }
   input[type=submit] { width : 100px; }
   #goList  { width : 80px; }
   
	   td { 
	      padding    : 10px;
	      width      : 700px;
	      text-align : center;
	   }
	   td:nth-of-type(1) {
	      width      : 200px;
	   }
	   
	   td:not([colspan]):first-child {
	      background : lightblue;
	      color      : black;
	      font-weight: bold;
	   }
	   
	   input[readonly] {
	      background : #EEE;
	   }

</style>
</head>
<body>
  <main>
	<h2>로그인</h2>
	<form action="/login" method="POST">
	<table>
	 <tr>
	   <td>ID</td>
	   <td><input type="text" name="userid" /></td>
	 </tr>
	 <tr>
	   <td>PW</td>
	   <td><input type="password" name="passwd" /></td>
	 </tr>
	 <tr>
	   <td colspan="2">
	    <input type="submit" value="추가" />
	    <input type="button" value="목록" id="goList" />
	   </td>
	 </tr>
	
	</table>	
	</form>   
	
  </main>
  
  <script>
  	const  goListEl  = document.getElementById('goList');
  	goListEl.addEventListener('click', function(e) {
  		location.href = '/Users/List';
  	})
  
  </script>
  
</body>
</html>

