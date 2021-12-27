<!--------------------------------------------- validation for player modal ------------------------------------------->

function allowOnlyLetters(e, t) {
    if (window.event) { var charCode = window.event.keyCode; }
    else if (e) { var charCode = e.which; }
    else { return true; }
    if ((charCode > 64 && charCode < 91) || (charCode > 96 && charCode < 123) || (charCode == 32)) {
        $("#" + t.id).css('border-color', '');
        document.getElementById(t.id + "msg").innerHTML = "";
        return true;
    } else {
        $("#" + t.id).css('border-color', 'red');
        document.getElementById(t.id + "msg").innerHTML = "Please enter only alphabets";
        return false;
    }
}

<!------------------------------------    Add player validation-------------------------------------->

function checkTeamSelect() {
    var teamId = $("#team_select option:selected").val();
    if (teamId == 0) {
        $("#playermsg").css('border-color', 'red');
        $("#playermsg").html("Please select all fields");
        $("#team_select").css('border-color', 'red');
        document.getElementById("playerSubmit").disabled = true;
        return true;
    } else {
        $("#playermsg").html("");
        $("#team_select").css('border-color', '');
        document.getElementById("playerSubmit").disabled = false;
        return false;
    }
}

function checkRoleSelect() {
    var roleId = $("#role_select option:selected").val();
    if (roleId == 0) {
        $("#playermsg").css('border-color', 'red');
        $("#playermsg").html("Please select all fields");
        $("#role_select").css('border-color', 'red');
        document.getElementById("playerSubmit").disabled = true;
        return true;
    } else {
        $("#playermsg").html("");
        $("#role_select").css('border-color', '');
        document.getElementById("playerSubmit").disabled = false;
        return false;
    }
}

function checkCountrySelect() {
    var countryId = $("#country_select option:selected").val();
    if (countryId == 0) {
        $("#playermsg").css('border-color', 'red');
        $("#playermsg").html("Please select all fields");
        $("#country_select").css('border-color', 'red');
        document.getElementById("playerSubmit").disabled = true;
        return true;
    } else {
        $("#playermsg").html("");
        $("#country_select").css('border-color', '');
        document.getElementById("playerSubmit").disabled = false;
        return false;
    }
}

function checkPlayer() {
    var roleId = $("#role_select option:selected").val();
    var teamId = $("#team_select option:selected").val();
    var countryId = $("#country_select option:selected").val();
    if (roleId == 0 || teamId == 0 || countryId == 0) {
        $("#playermsg").css('border-color', 'red');
        $("#playermsg").html("Please select all fields");
        $("#country_select").css('border-color', 'red');
        $("#team_select").css('border-color', 'red');
        $("#role_select").css('border-color', 'red');
        document.getElementById("playerSubmit").disabled = true;
        return true;
    } else {
        $("#playermsg").html("");
        $("#role_select").css('border-color', '');
        $("#team_select").css('border-color', '');
        $("#country_select").css('border-color', '');
        document.getElementById("playerSubmit").disabled = false;
        return false;
    }
}

function checkPlayerName() {
    var playerName = $("#player_name").val();
    if (playerName.length < 4) {
        document.getElementById("playerSubmit").disabled = true;
        $("#playermsg").html("Please enter name, contains atleast 4 characters");
        return true;
    } else {
        document.getElementById("playerSubmit").disabled = false;
        $("#playermsg").html("");
        return false;
    }
}

function playerSub() {
    if (checkPlayerName() || checkPlayer()) {
        document.getElementById("playerSubmit").disabled = true;
        $("#playermsg").html("Please fill all the fields....");
    }
}

<!-------------------------------------- Validation for date time picker in matches modal ---------------------------------------->

var today = new Date();
var dd = String(today.getDate()).padStart(2, '0');
var mm = String(today.getMonth() + 1).padStart(2, '0'); //January is 0!
var yyyy = today.getFullYear();
var dateControl = document.querySelector('input[type="datetime-local"]');
var time = today.getHours() + ':' + today.getMinutes();
dateControl.min = yyyy + '-' + mm + '-' + dd + 'T' + time;
console.log(dateControl.min);
dateControl.value = dateControl.min;

<!---------------------------------- DateTIme Validation and venue select same day ------------------------------------------------>

function matchFormSubmit() {
    if (checkVenueDate()) {
        $("#matchMsg").html("This slot is booked for other match. Please select another date or venue");
        document.getElementById("matchSubmit").disabled = true;
    }else if(matchScheduleValidate()){
        $("#matchMsg").html("Please fill all the fields");
        document.getElementById("matchSubmit").disabled = true;
    }
<!--    if(team2Options()){-->

<!--    }-->
}

function checkVenueDate() {
var dateControl1 = document.querySelector('input[type="datetime-local"]').value;
    var selectedDate = new Date(dateControl1);
    var date1 = selectedDate.getFullYear() + ',' + (selectedDate.getMonth() + 1) + ',' + selectedDate.getDate();
    var venueSelected = $("#venue_select option:selected").html();
    if (venueId == 0) {
        var venueId = $("#venue_select option:selected").val();
        $("#matchMsg").html("Please select the venue");
        $("#venue_select").css('border-color', 'red');
        document.getElementById("matchSubmit").disabled = true;
        return true;
    } else {
        $("#matchMsg").html("");
        $("#venue_select").css('border-color', '');
        document.getElementById("matchSubmit").disabled = false;
    }
    if (isSlotBooked(venueSelected, date1)) {
        return true;
    }
    console.log("Runnnig------------- ");
}

function isSlotBooked(venueSelected, date1) {
    var n1 = document.getElementById("tableData").rows.length;
    var i = 0, j = 0;
    for (i = 1; i < n1; i++) {
        var n2 = document.getElementById("tableData").rows[i].cells.length;
        var x = document.getElementById("tableData").rows[i].cells.item(5).innerHTML;
        var tableVenue = document.getElementById("tableData").rows[i].cells.item(4).innerHTML;
        var datetime = x.split(" "); //-- 2021-12-13
        var date = datetime[0].split("-");
        var date2 = date[0] + "," + date[1] + "," + date[2];
        if (date1 == "NaN,NaN,NaN") {
            console.log("condistion trye ", venueSelected);
            return true;
        }
        function diff_minutes(dt2, dt1) {
            var diff = (dt2.getTime() - dt1.getTime()) / 1000;
            diff /= 60;
            return Math.abs(Math.round(diff));
        }
        console.log("-------Function is running-------");
        dt1 = new Date(date1);
        dt2 = new Date(date2);
        var timeDiff = diff_minutes(dt1, dt2) / 60;
        if (timeDiff == 0 && venueSelected == tableVenue) {
            $("#venue_select").css('border-color', 'red');
            $("#date_picker").css('border-color', 'red');
            $("#matchMsg").html("This slot is booked for other match. Please select another date");
            document.getElementById("matchSubmit").disabled = true;
            return true;
        } else if (timeDiff != 0 || venueSelected != tableVenue) {
            $("#matchMsg").html("");
            $("#venue_select").css('border-color', '');
            $("#date_picker").css('border-color', '');
            document.getElementById("matchSubmit").disabled = false;
        }
    }
}

<!---------------------------------- Validation for Team selection in matches modal --------------------------------------------->

function team2Options() {
    var team2Id = $("#team2_select option:selected").val();
    var team1Id = $("#team1_select option:selected").val();
    if (team2Id == 0) {
        $("#matchMsg").html("Please select team-2");
        document.getElementById("matchSubmit").disabled = true;
        $("#team2_select").css('border-color', 'red');
        return true;
    }
<!--    else {-->
<!--        $("#matchMsg").html("");-->
<!--        document.getElementById("matchSubmit").disabled = false;-->
<!--        $("#team2_select").css('border-color', '');-->
<!--    }-->
    if (team1Id == team2Id) {
        $("#matchMsg").html("Team cannot be same. Please select different team instead");
        $("#team1_select").css('border-color', 'red');
        $("#team2_select").css('border-color', 'red');
        document.getElementById("matchSubmit").disabled = true;
        return true;
    }
    else if (team1Id != team2Id) {
        $("#matchMsg").html("");
        $("#team1_select").css('border-color', '');
        $("#team2_select").css('border-color', '');
        document.getElementById("matchSubmit").disabled = false;
    }
}

function team1Options() {
    var team2Id = $("#team2_select option:selected").val();
    var team1Id = $("#team1_select option:selected").val();
    if (team1Id == 0) {
        $("#matchMsg").html("Please select team-1");
        document.getElementById("matchSubmit").disabled = true;
        $("#team1_select").css('border-color', 'red');
        return true;
    }
    if (team1Id == team2Id) {
        $("#matchMsg").html("Team cannot be same. Please select different team instead");
        $("#team1_select").css('border-color', 'red');
        $("#team2_select").css('border-color', 'red');
        document.getElementById("matchSubmit").disabled = true;
        return true;
    }
    else if (team1Id != team2Id) {
        $("#matchMsg").html("");
        $("#team1_select").css('border-color', '');
        $("#team2_select").css('border-color', '');
        document.getElementById("matchSubmit").disabled = false;
    }
}

function checkVenue() {
    if (venueId == 0) {
        $("#matchMsg").html("Please select the venue");
        $("#venue_select").css('border-color', 'red');
        document.getElementById("matchSubmit").disabled = true;
        return true;
    } else {
        $("#matchMsg").html("");
        $("#venue_select").css('border-color', '');
        document.getElementById("matchSubmit").disabled = false;
    }
}
function matchScheduleValidate() {
    var venueId = $("#venue_select option:selected").val();
    var team1Id = $("#team1_select option:selected").val();
    var team2Id = $("#team2_select option:selected").val();

    if (team1Id == 0 || team2Id == 0) {
        $("#matchMsg").html("Please select all the fields..");
        document.getElementById("matchSubmit").disabled = true;
        return true;
    }
    else if (team1Id == team2Id) {
        $("#matchMsg").html("Team cannot be same. Please select different team instead");
        document.getElementById("matchSubmit").disabled = true;
        $("#team1_select").css('border-color', 'red');
        $("#team2_select").css('border-color', 'red');
        return true;
    }
    else if (team1Id != team2Id) {
        $("#matchMsg").html("");
        $("#team1_select").css('border-color', '');
        $("#team2_select").css('border-color', '');
        document.getElementById("matchSubmit").disabled = false;
    }
    if (venueId == 0) {
        $("#matchMsg").html("Please select the venue");
        $("#venue_select").css('border-color', 'red');
        document.getElementById("matchSubmit").disabled = true;
    } else {
        $("#matchMsg").html("");
        $("#venue_select").css('border-color', '');
        document.getElementById("matchSubmit").disabled = false;
    }
}
<!---------------------------------------- Adding team Validation -------------------------->
<!------------------------------------- Validation for Add team modal------------------------------------->

function validateCity() {
    var cityId = $("#select_city option:selected").val();
    var citySelected = $("#select_city option:selected").html();
    if (cityId == 0) {
<!--        alert("Please select the city");-->
        $("#teamNamemsg").html("Please select the city");
        $("#select_city").css('border-color', 'red');
        document.getElementById("teamSubmit").disabled = true;
        return true;
    }else{
        $("#teamNamemsg").html("");
        $("#select_city").css('border-color', '');
        document.getElementById("teamSubmit").disabled = false;
    }
    var n1 = document.getElementById("teamTable").rows.length;
    var i = 0, j = 0;
    for (i = 1; i < n1; i++) {
        var n2 = document.getElementById("teamTable").rows[i].cells.length;
        var city = document.getElementById("teamTable").rows[i].cells.item(3).innerHTML;
        if (citySelected == city) {
            document.getElementById("teamSubmit").disabled = true;
            $("#teamNamemsg").css('border-color', 'red');
            $("#teamNamemsg").html("One state can only have one team");
            return true;
        } else {
            document.getElementById("teamSubmit").disabled = false;
            $("#teamNamemsg").css('border-color', '');
            $("#teamNamemsg").html("");
        }
    }
}

function checkTeamName() {
    var inputName = $("#teamName").val();
    if (inputName.length < 1) {
        document.getElementById("teamSubmit").disabled = true;
        $("#teamNamemsg").html("Please enter name, contains atleast 2 characters");
        return true;
    } else {
        document.getElementById("teamSubmit").disabled = false;
        $("#teamNamemsg").html("");
    }
    var n1 = document.getElementById("teamTable").rows.length;
    var i = 0, j = 0;
    for (i = 1; i < n1; i++) {
        var n2 = document.getElementById("teamTable").rows[i].cells.length;
        var teamName = document.getElementById("teamTable").rows[i].cells.item(2).innerHTML;
        if (teamName == inputName) {
            document.getElementById("teamSubmit").disabled = true;
            $("#teamNamemsg").css('border-color', 'red');
            $("#teamNamemsg").html("This name is already exist, please enter different team name");
            return true;
        } else {
            document.getElementById("teamSubmit").disabled = false;
            $("#teamNamemsg").css('border-color', '');
        }
    }
}
function teamSub() {
    if (checkTeamName() || validateCity()) {
        document.getElementById("teamSubmit").disabled = true;
    }
}

<!--$('.mybutton').click(function() {-->
<!--  if ($(".input").val().trim() == '')-->
<!--    $(".playermsg").css('border-color', 'red');-->
<!--  else-->
<!--    $(".input").css('border-color', '');-->
<!--});-->

//***************************************************************************************
const cuteAlert = ({
  type,
  title,
  message,
  img,
  buttonText = 'OK',
  confirmText = 'OK',
  vibrate = [],
  playSound = null,
  cancelText = 'Cancel',
  closeStyle,
}) => {
  return new Promise(resolve => {
    const existingAlert = document.querySelector('.alert-wrapper');

    if (existingAlert) {
      existingAlert.remove();
    }

    const body = document.querySelector('body');

    const scripts = document.getElementsByTagName('script');

    let src = '';

    for (let script of scripts) {
      if (script.src.includes('cute-alert.js')) {
        src = script.src.substring(0, script.src.lastIndexOf('/'));
      }
    }

    let btnTemplate = `
    <button class="alert-button ${type}-bg-btn ${type}-btn">${buttonText}</button>
    `;

    if (type === 'question') {
      btnTemplate = `
      <div class="question-buttons">
        <button class="confirm-button ${type}-bg ${type}-btn">${confirmText}</button>
        <button class="cancel-button error-bg error-btn">${cancelText}</button>
      </div>
      `;
    }

    if (vibrate.length > 0) {
      navigator.vibrate(vibrate);
    }

    if (playSound !== null) {
      let sound = new Audio(playSound);
      sound.play();
    }

    const template = `
    <div class="alert-wrapper">
      <div class="alert-frame">
        ${img !== '' ? '<div class="alert-header ' + type + '-bg">' : '<div>'}
          <span class="alert-close ${
            closeStyle === 'circle'
              ? 'alert-close-circle'
              : 'alert-close-default'
          }">X</span>
          ${img !== '' ? '<img class="alert-img" src="' + src + '/' +img + '" />' : ''}
        </div>
        <div class="alert-body">
          <span class="alert-title">${title}</span>
          <span class="alert-message">${message}</span>
          ${btnTemplate}
        </div>
      </div>
    </div>
    `;

    body.insertAdjacentHTML('afterend', template);

    const alertWrapper = document.querySelector('.alert-wrapper');
    const alertFrame = document.querySelector('.alert-frame');
    const alertClose = document.querySelector('.alert-close');

    if (type === 'question') {
      const confirmButton = document.querySelector('.confirm-button');
      const cancelButton = document.querySelector('.cancel-button');

      confirmButton.addEventListener('click', () => {
        alertWrapper.remove();
        resolve('confirm');
      });

      cancelButton.addEventListener('click', () => {
        alertWrapper.remove();
        resolve();
      });
    } else {
      const alertButton = document.querySelector('.alert-button');

      alertButton.addEventListener('click', () => {
        alertWrapper.remove();
        resolve('ok');
      });
    }

    alertClose.addEventListener('click', () => {
      alertWrapper.remove();
      resolve('close');
    });

/*     alertWrapper.addEventListener('click', () => {
      alertWrapper.remove();
      resolve();
    }); */

    alertFrame.addEventListener('click', e => {
      e.stopPropagation();
    });
  });
};