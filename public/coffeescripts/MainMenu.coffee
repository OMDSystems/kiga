class MainMenu extends Ext.tree.AsyncTreeNode
  constructor: ->
    super(
      expanded: true
      children: [
        text: 'Children'
        leaf: true
        targetView: ""
      ,
        text: 'Groups'
        leaf: true
      ])