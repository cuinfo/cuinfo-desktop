function getRelativePath()

{

    var strLocation = window.location.pathname;

    var uri = strLocation.substring(0, strLocation.lastIndexOf("/") + 1);

    return uri;

}

function isInteger(string, sign)

{

    var integer;

    if ((sign != null) && (sign != '-') && (sign != '+'))

    {

        alert('IsInter(string,sign)�Ĳ������� signΪnull��"-"��"+"');

        return false;

    }

    integer = parseInt(string);

    if (isNaN(integer))

    {

        return false;

    }

    else if (integer.toString().length == string.length)

    {

        if ((sign == null) || (sign == '-' && integer < 0) || (sign == '+' && integer > 0))

        {

            return true;

        }

        else

            return false;

    }

    else

        return false;

}


function searchparams()

{

    if (((document.all.searchSpareSign.value == "���ɷ����ڲ�ѯ") ||

         (document.all.searchSpareSign.value == "����Ч���ڲ�ѯ") ||

         (document.all.searchSpareSign.value == "��ʧЧ���ڲ�ѯ")) && ((document.all.table1.style.display = "none" ) && (document.all.table2.style.display = "none")))

        document.all.table1.style.display = "";

    else

        ((document.all.table1.style.display = "none") && (document.all.table2.style.display = "" ))


}

function allhospital()

{

    if (document.all.table1.style.display == "none")

        document.all.table1.style.display = "";

    else

        document.all.table1.style.display = "none";

}

function prescriptionParams()

{

    if ((document.all.searchSpareSign.value == "���������ڲ�ѯ") && (document.all.table1.style.display = "none" ) && (document.all.table2.style.display = "none" ))



        document.all.table1.style.display = "";

    else  if (document.all.searchSpareSign.value == "��ҽԺ���Ʋ�ѯ")

        ((document.all.table1.style.display = "") && (document.all.table2.style.display = "" ));

    else

        ((document.all.table1.style.display = "none") && (document.all.table2.style.display = "" ));


}

function payok()

{

    document.all.needPay.value = "ok";

}

function notpay()

{

    document.all.needPay.value = "no";

}
// add by  zhaohu  
function displayGettingData()
{
    document.all.gettingData.style.display = "";
}
function hideGettingData()
{
    document.all.gettingData.style.display = "none";
}
//ѡ��ɱ����
function allInsuranceParamsListForChoose()
{
    displayGettingData();
    var paramsInformation = window.showModalDialog('../settings/insuranceParamsListForChoose.smis?paramersSort=' + '1', "", "dialogHeight:700px;dialogWidth:700px");
    hideGettingData();
    if (paramsInformation != null)

    {
        document.all.paramersName.value = paramsInformation[paramsInformation.length - 3];
        document.all.paramersUnit.value = paramsInformation[paramsInformation.length - 2];
        document.all.paramersAttributeName.value = paramsInformation[paramsInformation.length - 1];
        document.all.publicParamsId.value = paramsInformation[paramsInformation.length - 4];
        document.all.paramersAttribute.value = paramsInformation[paramsInformation.length - 5];
    }
}
//ѡ��̶�����
function allInsuranceParamsInvariableListForChoose()
{
    displayGettingData();
    var paramsInformation = window.showModalDialog('../settings/insuranceParamsInvariableListForChoose.smis?paramersSort=' + '0', "", "dialogHeight:700px;dialogWidth:700px");
    hideGettingData();
    if (paramsInformation != null)

    {
        document.all.paramersName.value = paramsInformation[paramsInformation.length - 2];
        document.all.paramersUnit.value = paramsInformation[paramsInformation.length - 1];
        document.all.publicParamsId.value = paramsInformation[paramsInformation.length - 3];

    }
}
function hospitalListForChoose()
{

    var paramsInformation = window.showModalDialog('../operation/hospitalListForChoosing.jsp', "", "dialogHeight:600px;dialogWidth:700px");

    if (paramsInformation != null)

    {
        document.all.hospitalName.value = paramsInformation[paramsInformation.length - 1];
        document.all.hospitalNumber.value = paramsInformation[paramsInformation.length - 2];

    }
}
function hospitalListForChooseToSeach()
{

    var paramsInformation = window.showModalDialog('../operation/hospitalListForChoosing.jsp', "", "dialogHeight:600px;dialogWidth:700px");

    if (paramsInformation != null)

    {
        document.all.searchValue.value = paramsInformation[paramsInformation.length - 1];
        document.all.hospitalNumber.value = paramsInformation[paramsInformation.length - 2];

    }
}
function isCheck(tableObj) {
    var isCheck = false;
    for (i = 1; i < tableObj.rows.length; i++) {
        var oneTd = tableObj.rows[i].cells[1].childNodes[0];
        if (oneTd.checked)
            isCheck = true;
    }
    ;
    return isCheck;
}

//���������б���֤��
function isCheckParams(tableObj, row, tier) {
    var isCheck = false;
    for (i = row; i < tableObj.rows.length; i++) {
        var oneTd = tableObj.rows[i].cells[tier].childNodes[0];
        if (oneTd.checked)
            isCheck = true;
    }
    ;
    return isCheck;
}

//ҽ��Ŀ¼���������б���֤��
function isCheckParams(tableObj, row, tier) {
    var isCheck = false;
    for (i = row; i < tableObj.rows.length; i++) {
        var oneTd = tableObj.rows[i].cells[tier].childNodes[0];
        var oneTd1 = tableObj.rows[i].cells[tier+1].childNodes[0];
        if (oneTd.checked ||oneTd1.checked )
            isCheck = true;
    }
    ;
    return isCheck;
}


function checknum(num)
{
    if (isNaN(num) && num != '-') {
        alert('���������֣�');
        return '';
    }
    return num;
}
//���֤����Ч����֤ �˴�ֻ�Ը�ʽ��������֤ 
//�ں����Ľ��п��Զ���Ч�Խ�����֤
function checkIdentitycardNumber(str) {
    var returnStr = checkIdcard(str);
    if (returnStr == '��֤ͨ��!') {
        return true;
    } else {
        alert(returnStr);
        return false;
    }
}
function checkIdcard(idcard) {
    var Errors = new Array(
            "��֤ͨ��!",
            "���֤����λ������!",
            "���֤����������ڳ�����Χ���зǷ��ַ�!",
            "���֤����У�����!",
            "���֤�����Ƿ�!"
            );
    var area = {11:"����",12:"���",13:"�ӱ�",14:"ɽ��",15:"���ɹ�",21:"����",22:"����",23:"������",31:"�Ϻ�",32:"����",33:"�㽭",34:"����",35:"����",36:"����",37:"ɽ��",41:"����",42:"����",43:"����",44:"�㶫",45:"����",46:"����",50:"����",51:"�Ĵ�",52:"����",53:"����",54:"����",61:"����",62:"����",63:"�ຣ",64:"����",65:"�½�",71:"̨��",81:"���",82:"����",91:"����"}

    var idcard,Y,JYM;
    var S,M;
    var idcard_array = new Array();
    idcard_array = idcard.split("");
    //��������

    //��ݺ���λ������ʽ����
    switch (idcard.length) {
        case 15:
            if (area[parseInt(idcard.substr(0, 2))] == null) return Errors[4];
            if ((parseInt(idcard.substr(6, 2)) + 1900) % 4 == 0 || ((parseInt(idcard.substr(6, 2)) + 1900) % 100 == 0 && (parseInt(idcard.substr(6, 2)) + 1900) % 4 == 0 )) {
                ereg = /^[1-9][0-9]{5}[0-9]{2}((01|03|05|07|08|10|12)(0[1-9]|[1-2][0-9]|3[0-1])|(04|06|09|11)(0[1-9]|[1-2][0-9]|30)|02(0[1-9]|[1-2][0-9]))[0-9]{3}$/;
                //���Գ������ڵĺϷ���
            } else {
                ereg = /^[1-9][0-9]{5}[0-9]{2}((01|03|05|07|08|10|12)(0[1-9]|[1-2][0-9]|3[0-1])|(04|06|09|11)(0[1-9]|[1-2][0-9]|30)|02(0[1-9]|1[0-9]|2[0-8]))[0-9]{3}$/;
                //���Գ������ڵĺϷ���
            }
            if (ereg.test(idcard)) return Errors[0];
            else return Errors[2];
            break;
        case 18:
            if (area[parseInt(idcard.substr(0, 2))] == null) return Errors[4];
            //18λ��ݺ�����
            //�������ڵĺϷ��Լ��
            //��������:((01|03|05|07|08|10|12)(0[1-9]|[1-2][0-9]|3[0-1])|(04|06|09|11)(0[1-9]|[1-2][0-9]|30)|02(0[1-9]|[1-2][0-9]))
            //ƽ������:((01|03|05|07|08|10|12)(0[1-9]|[1-2][0-9]|3[0-1])|(04|06|09|11)(0[1-9]|[1-2][0-9]|30)|02(0[1-9]|1[0-9]|2[0-8]))
            if (parseInt(idcard.substr(6, 4)) % 4 == 0 || (parseInt(idcard.substr(6, 4)) % 100 == 0 && parseInt(idcard.substr(6, 4)) % 4 == 0 )) {
                ereg = /^[1-9][0-9]{5}19|20[0-9]{2}((01|03|05|07|08|10|12)(0[1-9]|[1-2][0-9]|3[0-1])|(04|06|09|11)(0[1-9]|[1-2][0-9]|30)|02(0[1-9]|[1-2][0-9]))[0-9]{3}[0-9Xx]$/;
                //����������ڵĺϷ���������ʽ
            } else {
                ereg = /^[1-9][0-9]{5}19|20[0-9]{2}((01|03|05|07|08|10|12)(0[1-9]|[1-2][0-9]|3[0-1])|(04|06|09|11)(0[1-9]|[1-2][0-9]|30)|02(0[1-9]|1[0-9]|2[0-8]))[0-9]{3}[0-9Xx]$/;
                //ƽ��������ڵĺϷ���������ʽ
            }
            if (ereg.test(idcard)) {//���Գ������ڵĺϷ���
                //����У��λ
                S = (parseInt(idcard_array[0]) + parseInt(idcard_array[10])) * 7
                        + (parseInt(idcard_array[1]) + parseInt(idcard_array[11])) * 9
                        + (parseInt(idcard_array[2]) + parseInt(idcard_array[12])) * 10
                        + (parseInt(idcard_array[3]) + parseInt(idcard_array[13])) * 5
                        + (parseInt(idcard_array[4]) + parseInt(idcard_array[14])) * 8
                        + (parseInt(idcard_array[5]) + parseInt(idcard_array[15])) * 4
                        + (parseInt(idcard_array[6]) + parseInt(idcard_array[16])) * 2
                        + parseInt(idcard_array[7]) * 1
                        + parseInt(idcard_array[8]) * 6
                        + parseInt(idcard_array[9]) * 3;
                Y = S % 11;
                M = "F";
                JYM = "10X98765432";
                M = JYM.substr(Y, 1);
                //�ж�У��λ
                if (M == idcard_array[17]) return Errors[0]; //���ID��У��λ
                else return Errors[3];
            }
            else return Errors[2];
            break;
        default:
            return Errors[1];
            break;
    }

}

//�绰������֤
function checkPhone(str) {
    var reg = /(^[0-9]{3,4}\-[0-9]{3,8}$)|(^[0-9]{3,8}$)|(^\([0-9]{3,4}\)[0-9]{3,8}$)|(^0{0,1}[0-9]{11}$)/

    if (!reg.test(str)) {
        alert('��������ȷ�ĵ绰����:\n'
                + '(1)�绰���������֡�"("��")"��"-"����\n'
                + '(2)�绰����Ϊ3��8λ\n'
                + '(3)����绰�����а��������ţ���ô����Ϊ��λ����λ\n'
                + '(4)������"("��")"��"-"���������ָ���\n'
                + '(5)�ƶ��绰����Ϊ11��12λ�����Ϊ12λ,��ô��һλΪ0\n');
        return false;
    }
    else {
        return true;
    }
}
//�ͻ�����֤
function checkPersonNum(str) {
    var reg = /^\d{4}-\d{6}-\d{8}-\d{1}$/;
    if (reg.test(str)) {
        return true;
    }
    else {
        alert('��������ȷ�Ŀͻ��ţ�\n'
                + '��ȷ�Ŀͻ�����4λ��ݣ�6λ�����ţ�8λ��ˮ�ţ���1λʶ���룬�м���\'-\'���\n'
                + '��2006-510600-32356789-1\n');
        return false;
    }
}
//�����ӳ���
function checkLookExtend(str) {
   
    if (str>=0) {
        return true;
    }
    else {
        alert('�����ӳ��ڲ���Ϊ����');
        return false;
    }
}
//email��֤
function checkEmail(str) {
    var reg = /(\S)+[@]{1}(\S)+[.]{1}(\w)+/;
    if (reg.test(str)) {
        return true;
    }
    else {
        alert('��������ȷ��email��ַ��\n'
                + '�磺ybt@myjckj.com');
        return false;
    }
}
//��������֤
function checkOrganizationCode(str) {
    var reg = /^\d{6}$/;
    if (reg.test(str)) {
        return true;
    }
    else {
        alert('�����ű�����6λ�������.\n�磺510601');
    }
}
//�����������֤
function checkNumA(temp) {
    if (checkNull(temp) == true) {
        if (!(/^\d+(\.\d+)?$/.test(temp.value))) {
            alert("��������!");
            return false;
        }
        return true;
    }
}
//�ջ��߿ո���
function checkNull(temp)
{
    if (temp.value.length == 0 || temp.value.indexOf(" ") != -1)
    {
        alert("�������󣬲���Ϊ�ջ�����ո�");
        return false;
    }
    return true;
}
//����Ƿ�Ϊ��
function isNull(temp)
{
    if (temp.value.length == 0)
    {
        alert("�������󣬲���Ϊ��!");
        return false;
    }
    return true;
}
//��������֤ ����
function isNum(obj, str) {
    if (!/^(0|[1-9]\d{0,7})(.\d{1,2})?$/.test(obj.value)) {
        alert(str + "��ʽӦ��Ϊ:\n  99999999.99");

        return false;
    }
    return true;
}

//�ַ���Ϊ�ջ�����ո���֤
function checkString(str) {
    if (str == "" || str.indexOf(" ") != -1 || str.indexOf("��") != -1)
        return false;
    return true;
}


//��ʽ������
function FormatNumber(srcStr, nAfterDot) //nAfterDotС��λ��
{
    var srcStr,nAfterDot;
    var resultStr,nTen;
    srcStr = "" + srcStr + "";
    strLen = srcStr.length;
    dotPos = srcStr.indexOf(".", 0);
    if (dotPos == -1) {
        resultStr = srcStr + ".";
        for (i = 0; i < nAfterDot * 1; i++) {
            resultStr = resultStr + "0";
        }
        return resultStr;
    }
    else {
        if ((strLen - dotPos - 1) >= nAfterDot) {
            nAfter = dotPos + nAfterDot + 1;
            nTen = 1;
            for (j = 0; j < nAfterDot; j++) {
                nTen = nTen * 10;
            }
            resultStr = Math.round(parseFloat(srcStr) * nTen) / nTen;
            return resultStr;
        }
        else {
            resultStr = srcStr;
            for (i = 0; i < (nAfterDot - strLen + dotPos + 1); i++) {
                resultStr = resultStr + "0";
            }
            return resultStr;
        }
    }
}
 function checkIsNull (obj,str)       
       {
         if (obj.value == "" || obj.value.indexOf(" ") != -1 )
          {
            alert (str+"����Ϊ�ջ�����ո�!"); 
            return false;
        }
	     	return true;
       }
//���ڸ�ʽ
function formatTime(str)
{

  var   r   =   str.match(/^(\d{1,4})(-|\/)(\d{1,2})\2(\d{1,2})$/);     
  if(r==null) return   false;     
  var  d=  new  Date(r[1],   r[3]-1,   r[4]);     
  return  (d.getFullYear()==r[1]&&(d.getMonth()+1)==r[3]&&d.getDate()==r[4]);   

}

function CheckData(obj,str){
     if (obj.value=="")
   {
    alert(str+"����Ϊ�ջ�����ո�,������!");
    obj.focus();
   return false;
   } 
    if (!formatTime(obj.value))
   {
    alert(str+"��ʽ����!");
    obj.focus();
    return false;
    } 
   return true;
}

