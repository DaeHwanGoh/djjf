<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<meta name="viewport" content="width=device-width, initial-scale=1">
<script src="/ckeditor/ckeditor.js"></script>
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>

<!-- <a href=""><i class="glyphicon glyphicon-map-marker"></i></a>  아이콘으로 버튼만들기-->
<div class="col-md-10">
<div class="well" style="display: block; width: 700px;">
	<form class="form-horizontal" role="form" action="/post/store" method="post">
		<h4>What's New</h4>
		<div class="form-group" style="padding: 14px;">
			<textarea class="form-control" id="ckeditor" name="content"
				placeholder="Update your status"></textarea>
		</div>
		<button  class="btn btn-primary pull-right" type="submit">Post</button>
		<ul class="list-inline">
			<li></li>
			<li></li>
			<li></li>
		</ul>
	</form>
</div>
</div>
<script>
	$(document).ready(function() {
		CKEDITOR.replace('ckeditor', {//해당 이름으로 된 textarea에 에디터를 적용
			width : '100%',
			height : '400px',
			enterMode: '2',
			filebrowserImageUploadUrl : '/board/imageUpload' //여기 경로로 파일을 전달하여 업로드 시킨다.
		// 			this.addCss('p{ margin: 0 0 1.5em; }');
		});

		CKEDITOR.on('dialogDefinition', function(ev) {
			var dialogName = ev.data.name;
			var dialogDefinition = ev.data.definition;

			switch (dialogName) {
			case 'image': //Image Properties dialog
				//dialogDefinition.removeContents('info');
				dialogDefinition.removeContents('Link');
				dialogDefinition.removeContents('advanced');
				break;
			}
		});

	});
</script>