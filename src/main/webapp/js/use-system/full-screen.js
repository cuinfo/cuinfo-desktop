function OpenFullWin(Url)// ���´��ڲ����������closewin�����ر�ԭ���Ĵ���
{
	var width = screen.availWidth;
	var height = screen.availHeight;
	open(
			Url,
			'main',
			'width='
					+ (width)
					+ ',height='
					+ (height)
					+ ',left=0,top=0,menubar=no,toolbar=no,location=no,status=yes,scrollbars=no,resizable=no');
	if (window.parent == window)
		CloseWin();
}

function CloseWin()// �رմ���
{
	var ua = navigator.userAgent
	var ie = navigator.appName == "Microsoft Internet Explorer" ? true : false
	if (ie) {
		var IEversion = parseFloat(ua.substring(ua.indexOf("MSIE ") + 5, ua
				.indexOf(";", ua.indexOf("MSIE "))))
		if (IEversion < 5.5) {
			var str = '<object id=noTipClose classid="clsid:ADB880A6-D8FF-11CF-9377-00AA003B7A11">'
			str += '<param name="Command" value="Close"></object>';
			document.body.insertAdjacentHTML("beforeEnd", str);
			document.all.noTipClose.Click();
		} else {
			parent.opener = null;
			parent.close();
			// window.opener =null;
			// window.close();
		}
	}else{
            window.close();
        }
}