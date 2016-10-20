    function close_box(){
    	$(".right-box").removeClass('channel-on');
    	
    }
    
    function show_box(){
    	$(".right-box").addClass('channel-on');
    }
    
    $(".channel-bg").click(function(){
    	close_box();
    });

    $.extend({
        imGeeksUploader:function(id,url,cb){

            var uploader = new qq.FineUploader(
                {
                    element : document.getElementById(id),
                    request : {
                        endpoint : url
                    },
                    validation : {
                        allowedExtensions : [ 'jpg' , 'png']
                    },

                    text : {
                        uploadButton : '<i class="glyphicon glyphicon-plus"></i>上传头像<span style="padding-left:2px;font-weight:600;color:#de9090">></span>'
                    },
                    template : '<div class="qq-uploader">'
                    + '<pre class="qq-upload-drop-area"><span>{dragZoneText}</span></pre>'
                    + '<div class="qq-upload-button btn" style="padding:1px 6px">{uploadButtonText}</div>'
                    + '<span class="qq-drop-processing" style="display:none"><span>{dropProcessingText}</span>'
                    + '<span class="qq-drop-processing-spinner"></span></span>'
                    + '<ul class="qq-upload-list" style="margin-top: 10px; text-align: center;display:none"></ul>'
                    + '</div>',
                    classes : {
                        success : 'alert alert-success',
                        fail : 'alert alert-error'
                    },
                    callbacks : {
                        onComplete : function(id, fileName, responseJSON) {
                            cb(id,fileName,responseJSON);
                        }
                    }
                });


        }
    });