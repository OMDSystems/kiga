var MainMenu;
MainMenu = function() {
  return new Ext.tree.AsyncTreeNode({
    expanded: true,
    children: [
      {
        text: 'Children',
        leaf: true,
        targetView: ChildrenView
      }, {
        text: 'Groups',
        leaf: true
      }
    ]
  });
};