$(function() {
  $( "#date_of_birth" ).datepicker({dateFormat: 'dd.mm.yy'});

  var first_name = $( "#first_name" ),
    last_name = $( "#last_name" ),
    date_of_birth = $( "#date_of_birth" ),
    allergies = $( "#allergies" ),
    street = $( "#street" ),
    street_number = $( "#street_number" ),
    additional = $( "#additional" ),
    zip = $( "#zip" ),
    city = $( "#city" ),
    allFields = $( [] ).add( first_name ).add( last_name ).add( date_of_birth )
      .add( allergies ).add( street ).add( street_number ).add( additional )
      .add( zip ).add( city ),
    tips = $( ".validateTips" );

  function formUrlEncoded() {
    return "first_name=" + first_name.val() +
           "&last_name=" + last_name.val()  +
           "&date_of_birth=" + date_of_birth.val()  +
           "&allergies=" + allergies.val()  +
           "&street=" + street.val()  +
           "&street_number=" + street_number.val()  +
           "&additional=" + additional.val() +
           "&zip=" + zip.val() +
           "&city=" + city.val();
  }

  function submitForm() {
    $.ajax({
      type: "POST",
      url: "/children",
      data: formUrlEncoded(),
      success: function(msg) {
        $( "#children-table tbody" ).append( "<tr>" +
          "<td>" + last_name.val() + "</td>" +
          "<td>" + first_name.val() + "</td>" +
          "<td>" + date_of_birth.val() + "</td>" +
          "<td>" + allergies.val() + "</td>" +
          "<td>" + street.val() + " " + street_number.value + "</td>" +
          "<td>" + zip.val() + "</td>" +
          "<td>" + city.val() + "</td>" +
          "</tr>" );
        $( "#dialog-form" ).dialog( "close" );
      },
      error: function(error) {
        if(error.status == 0) {
          tips.text("Server unreachable.");
        } else if(error.status == 500) {
          tips.text("Internal server error.");
        } else {
          tips.text("Unknown error.");
        }
        $("#dialog-form").animate({scrollTop:0}, 300);
        tips.addClass( "ui-state-highlight" );
        setTimeout(function() {
          tips.removeClass( "ui-state-highlight", 1500 );
        }, 500 );
      }
    });
  }

  function checkFormValidity() {
    var bValid = true;
    allFields.each(function(index, field) {
      if(!field.checkValidity()) {
        $(field).addClass("invalid-input");
        bValid = false;
      }
    });
    setTimeout(function() {
      allFields.removeClass( "invalid-input", 500 );
    }, 1000);
    return bValid;
  }

  $( "#dialog-form" ).dialog({
    autoOpen: false,
    height: 700,
    width: 550,
    modal: true,
    buttons: {
      "Create an account": function() {
        if ( checkFormValidity() ) {
          submitForm();
        }
      },
      Cancel: function() {
        $( this ).dialog( "close" );
      }
    },
    close: function() {
      allFields.val( "" ).removeClass( "ui-state-error" );
    }
  });

  $( "#add-child" )
    .button()
    .click(function() {
      $( "#dialog-form" ).dialog( "open" );
  });

});

