var AddChildView;
var __hasProp = Object.prototype.hasOwnProperty, __extends = function(child, parent) {
  for (var key in parent) { if (__hasProp.call(parent, key)) child[key] = parent[key]; }
  function ctor() { this.constructor = child; }
  ctor.prototype = parent.prototype;
  child.prototype = new ctor;
  child.__super__ = parent.prototype;
  return child;
};
AddChildView = (function() {
  __extends(AddChildView, Ext.Window);
  function AddChildView() {
    AddChildView.__super__.constructor.call(this, {
      id: 'addChildView',
      width: 370,
      modal: true,
      resizable: false,
      draggable: false,
      items: [new AddChildForm()]
    });
  }
  return AddChildView;
})();