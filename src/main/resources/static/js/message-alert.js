function clickMatch() { }
function clickPlayer() { }
function clickPoint() { }
function clickTeam() { }
function clickScore() { }
window.onload = function () {
    var mb = document.getElementById("success");
    var messageType = /*[[${messageType}]]*/;
    var alertType = /*[[${alertType}]]*/;
    // < !--alert(test); -->

    if (alertType == 'success') {
        var imagePath = '../images/successAn3.gif';
        var title = "Success!";
         setTimeout(function () {
        $(".alert-wrapper").hide(1000);
        }, 2000);
    } else if (alertType == 'error') {
        var title = "Failed!";
        var imagePath = '../images/cross.jpg';
         setTimeout(function () {
        $(".alert-wrapper").hide(1000);
        }, 4000);
    }
    if (mb) {
        // < !--alert(alertType); -->
        var message = /*[[${message}]]*/;
        console.log($("#success").val());
        cuteAlert({
            type: alertType,
            title: title,
            message: message,
            buttonText: "OK",
            img: imagePath,
            closeStyle: "circle",
        });

        if (messageType == 'team') {
            $("#teamsTab-tab").click();
        }
        if (messageType == 'player') {
            $("#playersTab-tab").click();
        }
        if (messageType == 'match') {
            $("#matchesTab-tab").click();
        }
        if (messageType == 'score') {
            $("#scoresTab-tab").click();
        }
    }
}