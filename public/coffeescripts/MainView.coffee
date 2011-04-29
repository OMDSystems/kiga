class MainView extends Ext.Viewport
  constructor: ->
    super(
      layout: 'border',
      items: [
        region: 'north',
        html: '<h1 class="x-panel-header">SuperKiGaV</h1>',
        autoHeight: true,
        border: false,
        margins: '0 0 5 0'
      ,
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
        listeners:
          click: (n) ->
      ,
        new ChildrenView()
      ,
        region: 'south',
        title: 'Information',
        collapsible: true,
        html: 'Information goes here',
        split: true,
        height: 100,
        minHeight: 100
      ])