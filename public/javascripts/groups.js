function detailView(id) {
  $.ajax({
    type: "GET",
    url: "/groups/"+id,
    success: function(children) {
      openGroupDetailViewDialog(children);
    }
  });
}
