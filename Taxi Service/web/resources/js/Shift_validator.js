function validate_form ( ) {



    if(!checkCorrect(document.shiftTimeForm.timeIn.value)||
        !checkCorrect(document.shiftTimeForm.timeOut.value)) {
        return false;
    }
    if(convertInDate(document.shiftTimeForm.timeIn.value)>=convertInDate(document.shiftTimeForm.timeOut.value)){
        alert("Дата начала смены должна быть раньше даты ее окончания")
        return false;
    }
    return true;
}

function convertInDate(txt){

    var matches=txt.split(" ");
    var date=matches[0];
    var time=matches[1];

    if (matches === null) {
        alert ( "Пожалуйста заполните дату и время в виде 'год-месяц-день час:минута:секунда' " );
        return false;

    } else {
        // now lets check the date sanity
        var year = parseInt(date.split("-")[0]);
        var month = parseInt(date.split("-")[1]); // months are 0-11
        var day = parseInt(date.split("-")[2]);
        var hour = parseInt(time.split(":")[0]);
        var minute = parseInt(time.split(":")[1]);
        var second = parseInt(time.split(":")[2]);
        var date = new Date(year, month, day, hour, minute, second);
        return date;
    }
}



function checkCorrect(txt) {
    //var matches = document.shiftTimeForm.timeIn.value.match(/^(\d{4}).(\d{2}).(\d{2}).(\d{2}).(\d{2}).(\d{2})$/);
    var matches=txt.split(" ");
    var date=matches[0];
    var time=matches[1];



//alt:
// value.match(/^(\d{2}).(\d{2}).(\d{4}).(\d{2}).(\d{2}).(\d{2})$/);
// also matches 22/05/2013 11:23:22 and 22a0592013,11@23a22
    if (matches === null) {
        alert ( "Пожалуйста заполните дату и время в виде 'год-месяц-день час:минута:секунда' " );
        return false;

    } else{
        // now lets check the date sanity
        var year = parseInt(date.split("-")[0]);
        var month = parseInt(date.split("-")[1]); // months are 0-11
        var day = parseInt(date.split("-")[2]);
        var hour = parseInt(time.split(":")[0]);
        var minute = parseInt(time.split(":")[1]);
        var second = parseInt(time.split(":")[2]);
        var date = new Date(year, month, day, hour, minute, second);
        if (date.getFullYear() !== year
            || date.getMonth() != month
            || date.getDate() !== day
            || date.getHours() !== hour
            || date.getMinutes() !== minute
            || date.getSeconds() !== second
            || date.getFullYear()<2010
        ) {
            alert ( "Пожалуйста заполните дату и время в виде 'год-месяц-день час:минута:секунда' " );
            return false;
        } else {
            return true;
        }

    }

}

