class AddChildDialog extends Ext.Window

  constructor: ->
    super(
      width: 370,
      modal: true,
      resizable: false,
      draggable: false,
      items: [
        new AddChildForm()
      ]
    )