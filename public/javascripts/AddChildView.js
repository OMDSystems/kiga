var AddChildDialog;
var __hasProp = Object.prototype.hasOwnProperty, __extends = function(child, parent) {
  for (var key in parent) { if (__hasProp.call(parent, key)) child[key] = parent[key]; }
  function ctor() { this.constructor = child; }
  ctor.prototype = parent.prototype;
  child.prototype = new ctor;
  child.__super__ = parent.prototype;
  return child;
};
AddChildDialog = (function() {
  __extends(AddChildDialog, Ext.Window);
  function AddChildDialog() {
    AddChildDialog.__super__.constructor.call(this, {
      width: 370,
      modal: true,
      resizable: false,
      draggable: false,
      items: [new AddChildForm()]
    });
  }
  return AddChildDialog;
})();