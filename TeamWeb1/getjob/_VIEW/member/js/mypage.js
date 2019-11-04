$().ready(function(){
	$('#getSkills').click(function(){
		$.get("insertMemberSkill.jsp", function(data){
				$("#message").html(decodeURIComponent(data));
			});
	});
  $('#test').click(function(){
    alert("test");
  });
});
