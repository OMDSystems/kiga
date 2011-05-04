class AddChildView extends Ext.Window

  constructor: ->
    super(
      id: 'addChildView',
      width: 370,
      modal: true,
      resizable: false,
      draggable: false,
      items: [
        new AddChildForm()
      ]
    )