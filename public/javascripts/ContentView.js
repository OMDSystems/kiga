var ContentView;
var __hasProp = Object.prototype.hasOwnProperty, __extends = function(child, parent) {
  for (var key in parent) { if (__hasProp.call(parent, key)) child[key] = parent[key]; }
  function ctor() { this.constructor = child; }
  ctor.prototype = parent.prototype;
  child.prototype = new ctor;
  child.__super__ = parent.prototype;
  return child;
};
ContentView = (function() {
  __extends(ContentView, Ext.Panel);
  function ContentView() {
    ContentView.__super__.constructor.call(this, {
      region: 'center',
      id: 'center-panel',
      title: 'Content',
      split: true,
      collapsible: false
    });
  }
  return ContentView;
})();