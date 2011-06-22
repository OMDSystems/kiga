function openAssignGroupDialog(childId) {
  $("#assign-group-dialog").dialog("open");
}


$(function() {
  $( "#assign-group-dialog" ).dialog({
    autoOpen: false,
    height: 400,
    width: 910,
    modal: true,
    buttons: {
      Add: function() {
        $(this).dialog("close");
      },
      Close: function() {
        $(this).dialog("close");
      }
    },
    close: function() {
      uncheckAllRadio();
    }
  });
});

function uncheckAllRadio() {
  var inputs = $('input[type="radio"]');
  inputs.each(function(i, radio) {
    radio.checked = false;
  });
}

function uncheckRadio(btn) {
  btn.checked = false;
}


