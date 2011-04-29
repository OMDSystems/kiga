var MainMenu;
var __hasProp = Object.prototype.hasOwnProperty, __extends = function(child, parent) {
  for (var key in parent) { if (__hasProp.call(parent, key)) child[key] = parent[key]; }
  function ctor() { this.constructor = child; }
  ctor.prototype = parent.prototype;
  child.prototype = new ctor;
  child.__super__ = parent.prototype;
  return child;
};
MainMenu = (function() {
  __extends(MainMenu, Ext.tree.AsyncTreeNode);
  function MainMenu() {
    MainMenu.__super__.constructor.call(this, {
      expanded: true,
      children: [
        {
          text: 'Children',
          leaf: true,
          targetView: ""
        }, {
          text: 'Groups',
          leaf: true
        }
      ]
    });
  }
  return MainMenu;
})();