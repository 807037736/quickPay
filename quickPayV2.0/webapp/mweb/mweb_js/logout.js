function GetJSON(url, data, onSucess, onError) {
	$.ajax({
		type : "GET",
		url : url,
		data : data,
		success : function(rtd) {
			if (onSucess != null) {
				onSucess(rtd);
			}
		},
		dataType : 'json',
		async : false,
		cache : false,
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			if (onError != null) {
				onError(errorThrown);
			}
		}
	});
}

function Logout() {
	GetJSON(mobileSsoServerUrl + "/Ajax.sso?format=json&callback=?", {
		SF_OP : 'LogoutSsoSession'
	}, function(Response) {
		var bizSrvsArray = Response.returnObject;
		if (bizSrvsArray != null && bizSrvsArray.length > 0) {
			for ( var i = 0; i < bizSrvsArray.length; i++) {
				var url =window.location.href ;
				var serviceUrl = bizSrvsArray[i];
				if(url.substr(0,5) == "https"){
					serviceUrl=serviceUrl.replace("http:","https:");
				}
				GetJSON(serviceUrl + "/SsoLogout.sso?format=json&callback=?");
			}
		}
        GetJSON(mobileEfsServerUrl+"/SsoLogout.sso?format=json&callback=?");       
        GetJSON(mobileLesServerUrl+"/SsoLogout.sso?format=json&callback=?");       
		var topwindow=getParentWindow(window);
	    topwindow.location.replace(mobileEfsServerUrl+"/mEservice");
		
        
	}, function(error) {
		 alert(error);
	});
};

function logoutSession(callback) {
	GetJSON(mobileSsoServerUrl + "/Ajax.sso?format=json&callback=?", {
		SF_OP : 'LogoutSsoSession'
	}, function(Response) {
		var bizSrvsArray = Response.returnObject;
		if (bizSrvsArray != null && bizSrvsArray.length > 0) {
			for ( var i = 0; i < bizSrvsArray.length; i++) {
				var url =window.location.href ;
				var serviceUrl = bizSrvsArray[i];
				if(url.substr(0,5) == "https"){
					serviceUrl=serviceUrl.replace("http:","https:");
				}
				GetJSON(serviceUrl + "/SsoLogout.sso?format=json&callback=?");
			}
		}        
        callback();		
        
	}, function(error) {
		 alert(error);
	});
};

function getParentWindow(w){
	if(w.parent == null || w == w.parent){
		return w;
	}else{
		return getParentWindow(w.parent);
	}
}