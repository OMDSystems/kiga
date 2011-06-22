function openGroupDetailViewDialog(msg) {
  var groupDetailView = $( "#group-detail-modal" );
  var childrenHtml = "";
  msg.forEach(function(child) {
    childrenHtml += "<p>" + child.name + " " + child.familyName + "</p>\n"
  });
  groupDetailView.html(childrenHtml)
  groupDetailView.dialog("open");
}


$(function() {
  $( "#group-detail-modal" ).dialog({
    autoOpen: false,
    height: 350,
    modal: true
  });
});

