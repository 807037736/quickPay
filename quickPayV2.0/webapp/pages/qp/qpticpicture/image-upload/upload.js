(function( $ ){
    // 当domReady的时候开始初始化
    $(function() {
    	//首先要加载照片组号
    	if($("#surveyGroupId").val()==""){
		    $.ajax({
				type : "POST",
				url : '${ctx}/qp/qpticpicture/createSurveyGroup.do?accidentAcciId=' + $("#accidentAcciId").val()+'&surveyGroupId=' + $("#surveyGroupId").val(),
				async : false,
				cache : false, //缓存
				global : false,
				success : function(result) {
					var obj = eval("(" + result + ")");
					$("#surveyGroupId").val(obj.msg);
					window.opener.document.getElementById("qpTICAccidentSurveyGroupId").value = obj.msg;
					setParmsValue('SurveyGroupId',obj.msg);
				},
				error : function(XMLHttpRequest, textStatus, errorThrown) {
					$.messager.alert('提示信息', '处理照片组号出错，请重试！', 'info');
				}
			});
    	}
    	
    	var BASE_URL = contextRootPath + "/pages/qp/qpticpicture/image-upload/Uploader.swf";
        var $wrap = $('#uploader'),

            // 图片容器
            $queue = $( '<ul class="filelist"></ul>' )
                .appendTo( $wrap.find( '.queueList' ) ),

            // 状态栏，包括进度和控制按钮
            $statusBar = $wrap.find( '.statusBar' ),

            // 文件总体选择信息。
            $info = $statusBar.find( '.info' ),

            // 上传按钮
            $upload = $wrap.find( '.uploadBtn' ),

            // 没选择文件之前的内容。
            $placeHolder = $wrap.find( '.placeholder' ),

            $progress = $statusBar.find( '.progress' ).hide(),

            // 添加的文件数量
            fileCount = 0,

            // 添加的文件总大小
            fileSize = 0,

            // 优化retina, 在retina下这个值是2
            ratio = window.devicePixelRatio || 1,

            // 缩略图大小
            thumbnailWidth = 110 * ratio,
            thumbnailHeight = 110 * ratio,

            // 可能有pedding, ready, uploading, confirm, done.
            state = 'pedding',

            // 所有文件的进度信息，key为file id
            percentages = {},
            // 判断浏览器是否支持图片的base64
            isSupportBase64 = ( function() {
                var data = new Image();
                var support = true;
                data.onload = data.onerror = function() {
                    if( this.width != 1 || this.height != 1 ) {
                        support = false;
                    }
                },
                data.src = "data:image/gif;base64,R0lGODlhAQABAIAAAAAAAP///ywAAAAAAQABAAACAUwAOw==";
                return support;
            } )(),

            // 检测是否已经安装flash，检测flash的版本
            flashVersion = ( function() {
                var version;

                try {
                    version = navigator.plugins[ 'Shockwave Flash' ];
                    version = version.description;
                } catch ( ex ) {
                    try {
                        version = new ActiveXObject('ShockwaveFlash.ShockwaveFlash')
                                .GetVariable('$version');
                    } catch ( ex2 ) {
                        version = '0.0';
                    }
                }
                version = version.match( /\d+/g );
                return parseFloat( version[ 0 ] + '.' + version[ 1 ], 10 );
            } )(),

            supportTransition = (function(){
                var s = document.createElement('p').style,
                    r = 'transition' in s ||
                            'WebkitTransition' in s ||
                            'MozTransition' in s ||
                            'msTransition' in s ||
                            'OTransition' in s;
                s = null;
                return r;
            })(),

            // WebUploader实例
            uploader;

        if ( !WebUploader.Uploader.support('flash') && WebUploader.browser.ie ) {

            // flash 安装了但是版本过低。
            if (flashVersion) {
                (function(container) {
                    window['expressinstallcallback'] = function( state ) {
                        switch(state) {
                            case 'Download.Cancelled':
                                alert('您取消了更新！');
                                break;

                            case 'Download.Failed':
                                alert('安装失败');
                                break;

                            default:
                                alert('安装已成功，请刷新！');
                                break;
                        }
                        delete window['expressinstallcallback'];
                    };

                    var swf = './expressInstall.swf';
                    // insert flash object
                    var html = '<object type="application/' +
                            'x-shockwave-flash" data="' +  swf + '" ';

                    if (WebUploader.browser.ie) {
                        html += 'classid="clsid:d27cdb6e-ae6d-11cf-96b8-444553540000" ';
                    }

                    html += 'width="100%" height="100%" style="outline:0">'  +
                        '<param name="movie" value="' + swf + '" />' +
                        '<param name="wmode" value="transparent" />' +
                        '<param name="allowscriptaccess" value="always" />' +
                    '</object>';

                    container.html(html);

                })($wrap);

            // 压根就没有安转。
            } else {
                $wrap.html('<a href="http://www.adobe.com/go/getflashplayer" target="_blank" border="0"><img alt="get flash player" src="http://www.adobe.com/macromedia/style_guide/images/160x41_Get_Flash_Player.jpg" /></a>');
            }

            return;
        } else if (!WebUploader.Uploader.support()) {
            alert( 'Web Uploader 不支持您的浏览器！');
            return;
        }

        // 实例化
        uploader = WebUploader.create({
            pick: {
                id: '#filePicker',
                label: '点击选择图片'
            },
//            formData: {
//                uid: 123
//            },
            dnd: '#dndArea',
            paste: '#uploader',
            swf: BASE_URL,
            chunked: false,
            chunkSize: 512 * 1024,
            server: contextRootPath + '/qp/qpticpicture/doUpload.do?accidentAcciId=' + $("#accidentAcciId").val()+'&surveyGroupId=' + $("#surveyGroupId").val(),
            //server: $("#uploadImgUrl").val() +$("#surveyGroupId").val()+"?u="+$("#imgUser").val()+"&mac="+$("#mac").val()+"&data="+$("#dataTime").val(),
            // runtimeOrder: 'flash',
         // [默认值：'file']  设置文件上传域的name。 
          //fileVal:'upload', 
             accept: {
                 title: 'Images',
                 extensions: 'gif,jpg,jpeg,bmp,png',
                 mimeTypes: 'image/gif,image/jpg,image/jpeg,image/bmp,image/png'
             },
             //sendAsBinary:true,
            // 禁掉全局的拖拽功能。这样不会出现图片拖进页面的时候，把图片打开。
            disableGlobalDnd: true,
            fileNumLimit: 300,
            fileSizeLimit: 200 * 1024 * 1024,    // 200 M
            fileSingleSizeLimit: 50 * 1024 * 1024,   // 50 M
            // {Boolean} [可选] [默认值：false] 是否允许在文件传输时提前把下一个文件准备好。 对于一个文件的准备工作比较耗时，比如图片压缩，md5序列化。 如果能提前在当前文件传输期处理，可以节省总体耗时。
            prepareNextFile:true,
            compress: {
            	width: 1600,
                height: 1600,

                // 图片质量，只有type为`image/jpeg`的时候才有效。
                quality: 20,

                // 是否允许放大，如果想要生成小图的时候不失真，此选项应该设置为false.
                allowMagnify: false,

                // 是否允许裁剪。
                crop: false,

                // 是否保留头部meta信息。
                preserveHeaders: true,

                // 如果发现压缩后文件大小比原来还大，则使用原来图片
                // 此属性可能会影响图片自动纠正功能
                noCompressIfLarger: false,

                // 单位字节，如果图片大小小于此值，不会采用压缩。
                compressSize: 0
            }
        });

        // 拖拽时不接受 js, txt 文件。
        uploader.on( 'dndAccept', function( items ) {
            var denied = false,
                len = items.length,
                i = 0,
                // 修改js类型
                unAllowed = 'text/plain;application/javascript ';

            for ( ; i < len; i++ ) {
                // 如果在列表里面
                if ( ~unAllowed.indexOf( items[ i ].type ) ) {
                    denied = true;
                    break;
                }
            }

            return !denied;
        });

//        uploader.on('dialogOpen', function() {
//            console.log('here');
//        });

        // uploader.on('filesQueued', function() {
        //     uploader.sort(function( a, b ) {
        //         if ( a.name < b.name )
        //           return -1;
        //         if ( a.name > b.name )
        //           return 1;
        //         return 0;
        //     });
        // });

        // 添加“添加文件”的按钮，
        uploader.addButton({
            id: '#filePicker2',
            label: '继续添加'
        });

        uploader.on('ready', function() {
            window.uploader = uploader;
        });

        // 当有文件添加进来时执行，负责view的创建
        function addFile( file ) {
            var $li = $( '<li id="' + file.id + '">' +
                    '<p class="title">' + file.name + '</p>' +
                    '<p class="imgWrap"></p>'+
                    '<p class="progress"><span></span></p>' +
                    '</li>' ),

                $btns = $('<div class="file-panel">' +
                    '<span class="cancel">删除</span>' +
                    '<span class="rotateRight">向右旋转</span>' +
                    '<span class="rotateLeft">向左旋转</span></div>').appendTo( $li ),
                $prgress = $li.find('p.progress span'),
                $wrap = $li.find( 'p.imgWrap' ),
                $info = $('<p class="error"></p>'),

                showError = function( code ) {
                    switch( code ) {
                        case 'exceed_size':
                            text = '文件大小超出';
                            break;

                        case 'interrupt':
                            text = '上传暂停';
                            break;

                        default:
                            text = '上传失败，请重试';
                            break;
                    }

                    $info.text( text ).appendTo( $li );
                };

            if ( file.getStatus() === 'invalid' ) {
                showError( file.statusText );
            } else {
                // @todo lazyload
                $wrap.text( '预览中' );
                uploader.makeThumb( file, function( error, src ) {
                    var img;

                    if ( error ) {
                        $wrap.text( '不能预览' );
                        return;
                    }

                    if( isSupportBase64 ) {
                        img = $('<img src="'+src+'">');
                        $wrap.empty().append( img );
                    } else {
//                        $.ajax('../../server/preview.php', {
//                            method: 'POST',
//                            data: src,
//                            dataType:'json'
//                        }).done(function( response ) {
//                            if (response.result) {
//                                img = $('<img src="'+response.result+'">');
//                                $wrap.empty().append( img );
//                            } else {
//                                $wrap.text("预览出错");
//                            }
//                        });
                    }
                }, thumbnailWidth, thumbnailHeight );

                percentages[ file.id ] = [ file.size, 0 ];
                file.rotation = 0;
            }

            file.on('statuschange', function( cur, prev ) {
                if ( prev === 'progress' ) {
                    $prgress.hide().width(0);
                } else if ( prev === 'queued' ) {
                    $li.off( 'mouseenter mouseleave' );
                    $btns.remove();
                }

                // 成功
                if ( cur === 'error' || cur === 'invalid' ) {
                    console.log( file.statusText );
                    showError( file.statusText );
                    percentages[ file.id ][ 1 ] = 1;
                } else if ( cur === 'interrupt' ) {
                    showError( 'interrupt' );
                } else if ( cur === 'queued' ) {
                    percentages[ file.id ][ 1 ] = 0;
                } else if ( cur === 'progress' ) {
                    $info.remove();
                    $prgress.css('display', 'block');
                } else if ( cur === 'complete' ) {
                    $li.append( '<span class="success"></span>' );
                }

                $li.removeClass( 'state-' + prev ).addClass( 'state-' + cur );
            });

            $li.on( 'mouseenter', function() {
                $btns.stop().animate({height: 30});
            });

            $li.on( 'mouseleave', function() {
                $btns.stop().animate({height: 0});
            });

            $btns.on( 'click', 'span', function() {
                var index = $(this).index(),
                    deg;

                switch ( index ) {
                    case 0:
                        uploader.removeFile( file );
                        return;

                    case 1:
                        file.rotation += 90;
                        break;

                    case 2:
                        file.rotation -= 90;
                        break;
                }

                if ( supportTransition ) {
                    deg = 'rotate(' + file.rotation + 'deg)';
                    $wrap.css({
                        '-webkit-transform': deg,
                        '-mos-transform': deg,
                        '-o-transform': deg,
                        'transform': deg
                    });
                } else {
                    //$wrap.css( 'filter', 'progid:DXImageTransform.Microsoft.BasicImage(rotation='+ (~~((file.rotation/90)%4 + 4)%4) +')');
                    // use jquery animate to rotation
                    // $({
                    //     rotation: rotation
                    // }).animate({
                    //     rotation: file.rotation
                    // }, {
                    //     easing: 'linear',
                    //     step: function( now ) {
                    //         now = now * Math.PI / 180;

                    //         var cos = Math.cos( now ),
                    //             sin = Math.sin( now );

                    //         $wrap.css( 'filter', "progid:DXImageTransform.Microsoft.Matrix(M11=" + cos + ",M12=" + (-sin) + ",M21=" + sin + ",M22=" + cos + ",SizingMethod='auto expand')");
                    //     }
                    // });
                }


            });

            $li.appendTo( $queue );
        }

        // 负责view的销毁
        function removeFile( file ) {
            var $li = $('#'+file.id);

            delete percentages[ file.id ];
            updateTotalProgress();
            $li.off().find('.file-panel').off().end().remove();
        }

        function updateTotalProgress() {
            var loaded = 0,
                total = 0,
                spans = $progress.children(),
                percent;

            $.each( percentages, function( k, v ) {
                total += v[ 0 ];
                loaded += v[ 0 ] * v[ 1 ];
            } );

            percent = total ? loaded / total : 0;


            spans.eq( 0 ).text( Math.round( percent * 100 ) + '%' );
            spans.eq( 1 ).css( 'width', Math.round( percent * 100 ) + '%' );
            updateStatus();
        }

        function updateStatus() {
            var text = '', stats;

            if ( state === 'ready' ) {
                text = '选中' + fileCount + '张图片，共' +
                        WebUploader.formatSize( fileSize ) + '。';
            } else if ( state === 'confirm' ) {
                stats = uploader.getStats();
                if ( stats.uploadFailNum ) {
                    text = '已成功上传' + stats.successNum+ '张照片至系统，'+
                        stats.uploadFailNum + '张照片上传失败，<a class="retry" href="#">重新上传</a>失败图片或<a class="ignore" href="#">忽略</a>';
                }

            } else {
                stats = uploader.getStats();
                text = '共' + fileCount + '张（' +
                        WebUploader.formatSize( fileSize )  +
                        '），已上传' + stats.successNum + '张';

                if ( stats.uploadFailNum ) {
                    text += '，失败' + stats.uploadFailNum + '张';
                }
            }

            $info.html( text );
        }

        function setState( val ) {
            var file, stats;

            if ( val === state ) {
                return;
            }

            $upload.removeClass( 'state-' + state );
            $upload.addClass( 'state-' + val );
            state = val;

            switch ( state ) {
                case 'pedding':
                    $placeHolder.removeClass( 'element-invisible' );
                    $queue.hide();
                    $statusBar.addClass( 'element-invisible' );
                    uploader.refresh();
                    break;

                case 'ready':
                    $placeHolder.addClass( 'element-invisible' );
                    $( '#filePicker2' ).removeClass( 'element-invisible');
                    $queue.show();
                    $statusBar.removeClass('element-invisible');
                    uploader.refresh();
                    break;

                case 'uploading':
                    $( '#filePicker2' ).addClass( 'element-invisible' );
                    $progress.show();
                    $upload.text( '暂停上传' );
                    break;

                case 'paused':
                    $progress.show();
                    $upload.text( '继续上传' );
                    break;

                case 'confirm':
                    $progress.hide();
                    $( '#filePicker2' ).removeClass( 'element-invisible' );
                    $upload.text( '开始上传' );

                    stats = uploader.getStats();
                    if ( stats.successNum && !stats.uploadFailNum ) {
                        setState( 'finish' );
                        return;
                    }
                    break;
                case 'finish':
                    stats = uploader.getStats();
                    if ( stats.successNum ) {
                        alert( '上传成功' );
                    } else {
                        // 没有成功的图片，重设
                        state = 'done';
                        location.reload();
                    }
                    break;
            }

            updateStatus();
        }
        

        uploader.onUploadProgress = function( file, percentage ) {
            var $li = $('#'+file.id),
                $percent = $li.find('.progress span');

            $percent.css( 'width', percentage * 100 + '%' );
            percentages[ file.id ][ 1 ] = percentage;
            updateTotalProgress();
        };
        
        uploader.on( 'uploadBeforeSend', function( block, data ) {
            // block为分块数据。

            // file为分块对应的file对象。
            var file = block.file;

            // 修改data可以控制发送哪些携带数据。
            data.fileName = file.name;
            //data.fileType = file.ext;

            // 将存在file对象中的md5数据携带发送过去。
            // data.fileMd5 = file.md5;

            // 删除其他数据
            // delete data.key;
        });
//        uploader.on( 'uploadStart', function( File ) {
//        	alert(1);
//        	setTimeout(200);
//        });
        uploader.onFileQueued = function( file ) {
        	fileCount++;
        	fileSize += file.size;
        	
        	if ( fileCount === 1 ) {
        		$placeHolder.addClass( 'element-invisible' );
        		$statusBar.show();
        	}
        	
        	addFile( file );
        	setState( 'ready' );
        	updateTotalProgress();
        };
        
        uploader.on( 'uploadSuccess', function( file , response  ) {
        	console.log(response);
        	//window.opener.document.getElementById("qpTICAccidentSurveyGroupId").value = response.msg;
        });
        uploader.on( 'error', function( file , response  ) {
        	alert("上传文件出错！");
        });
        
        uploader.on('uploadAccept', function( file , ret  ) {
        	console.log(ret);
        	if(ret.state == '00' && ret.data.imgs[0].status == true){
        		$.ajax({
        			url: contextRootPath + '/qp/qpticpicture/doUpload.do?accidentAcciId=' + $("#accidentAcciId").val()+'&surveyGroupId=' + $("#surveyGroupId").val(),
        			dataType : "json",
        			data : ret.data.imgs[0] ,
        			success : function(result){
        				if(result.uploadStatus=='success'){
        	        		 return true;
        	        	 }else{
        	        		 return false;
        	        	 }
        			},
        			error :function(){
        				return false;
        			}
        		});
        	}else{
        		return false;
        	}
        });

        uploader.onFileDequeued = function( file ) {
            fileCount--;
            fileSize -= file.size;

            if ( !fileCount ) {
                setState( 'pedding' );
            }

            removeFile( file );
            updateTotalProgress();

        };

        uploader.on( 'all', function( type ) {
            var stats;
            switch( type ) {
                case 'uploadFinished':
                    setState( 'confirm' );
                    break;

                case 'startUpload':
                    setState( 'uploading' );
                    break;

                case 'stopUpload':
                    setState( 'paused' );
                    break;

            }
        });

        uploader.onError = function( code ) {
            alert( 'Eroor: ' + code );
        };

        $upload.on('click', function() {
            if ( $(this).hasClass( 'disabled' ) ) {
                return false;
            }

            if ( state === 'ready' ) {
                uploader.upload();
            } else if ( state === 'paused' ) {
                uploader.upload();
            } else if ( state === 'uploading' ) {
                uploader.stop();
            }
        });

        $info.on( 'click', '.retry', function() {
            uploader.retry();
        } );

        $info.on( 'click', '.ignore', function() {
            alert( 'todo' );
        } );

        $upload.addClass( 'state-' + state );
        updateTotalProgress();
    });
    
    
  //设置URL参数的方法  
    function setParmsValue(parms, parmsValue) {  
        var urlstrings = document.URL;  
        var args = GetUrlParms();  
        var values = args[parms];  
        //如果参数不存在，则添加参数         
        if (values == undefined) {  
            var query = location.search.substring(1); //获取查询串   
            //如果Url中已经有参数，则附加参数  
            if (query) {  
                urlstrings += ("&" + parms + "=" + parmsValue);  
            }  
            else {  
                urlstrings += ("?" + parms + "=" + parmsValue);  //向Url中添加第一个参数  
            }  
            window.location = urlstrings;  
        }  
        else {  
            window.location = updateParms(parms, parmsValue);  //修改参数  
        }  
    }  

    //修改URL参数，parms：参数名，parmsValue：参数值，return：修改后的URL  
    function updateParms(parms, parmsValue) {  
        var newUrlParms = "";  
        var newUrlBase = location.href.substring(0, location.href.indexOf("?") + 1); //截取查询字符串前面的url  
        var query = location.search.substring(1); //获取查询串     
        var pairs = query.split("&"); //在逗号处断开     
        for (var i = 0; i < pairs.length; i++) {  
            var pos = pairs[i].indexOf('='); //查找name=value     
            if (pos == -1) continue; //如果没有找到就跳过     
            var argname = pairs[i].substring(0, pos); //提取name     
            var value = pairs[i].substring(pos + 1); //提取value   
            //如果找到了要修改的参数  
            if (findText(argname, parms)) {  
                newUrlParms = newUrlParms + (argname + "=" + parmsValue + "&");  
            }  
            else {  
                newUrlParms += (argname + "=" + value + "&");  
            }  
        }  
        return newUrlBase + newUrlParms.substring(0, newUrlParms.length - 1);  
    }  

    //辅助方法  
    function findText(urlString, keyWord) {  
        return urlString.toLowerCase().indexOf(keyWord.toLowerCase()) != -1 ? true : false;  
    }  

    //得到查询字符串参数集合  
    function GetUrlParms() {  
        var args = new Object();  
        var query = location.search.substring(1); //获取查询串     
        var pairs = query.split("&"); //在逗号处断开     
        for (var i = 0; i < pairs.length; i++) {  
            var pos = pairs[i].indexOf('='); //查找name=value     
            if (pos == -1) continue; //如果没有找到就跳过     
            var argname = pairs[i].substring(0, pos); //提取name     
            var value = pairs[i].substring(pos + 1); //提取value     
            args[argname] = unescape(value); //存为属性     
        }  
        return args;  
    }  

})( jQuery );