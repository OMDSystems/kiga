var MainView;
var __hasProp = Object.prototype.hasOwnProperty, __extends = function(child, parent) {
  for (var key in parent) { if (__hasProp.call(parent, key)) child[key] = parent[key]; }
  function ctor() { this.constructor = child; }
  ctor.prototype = parent.prototype;
  child.prototype = new ctor;
  child.__super__ = parent.prototype;
  return child;
};
MainView = (function() {
  __extends(MainView, Ext.Viewport);
  function MainView() {
    MainView.__super__.constructor.call(this, {
      layout: 'border',
      items: [
        {
          region: 'north',
          html: '<h1 class="x-panel-header">SuperKiGaV</h1>',
          autoHeight: true,
          border: false,
          margins: '0 0 5 0'
        }, {
          region: 'west',
          collapsible: true,
          title: 'Navigation',
          xtype: 'treepanel',
          width: 200,
          autoScroll: true,
          split: true,
          loader: new Ext.tree.TreeLoader(),
          root: new MainMenu(),
          rootVisible: false,
          listeners: {
            click: function(n) {}
          }
        }, new ChildrenView(), {
          region: 'south',
          title: 'Information',
          collapsible: true,
          html: 'Information goes here',
          split: true,
          height: 100,
          minHeight: 100
        }
      ]
    });
  }
  return MainView;
})();