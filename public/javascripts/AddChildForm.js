var AddChildForm;
var __hasProp = Object.prototype.hasOwnProperty, __extends = function(child, parent) {
  for (var key in parent) { if (__hasProp.call(parent, key)) child[key] = parent[key]; }
  function ctor() { this.constructor = child; }
  ctor.prototype = parent.prototype;
  child.prototype = new ctor;
  child.__super__ = parent.prototype;
  return child;
};
AddChildForm = (function() {
  __extends(AddChildForm, Ext.form.FormPanel);
  function AddChildForm() {
    AddChildForm.__super__.constructor.call(this, {
      title: 'Form Layout',
      bodyStyle: 'padding:15px',
      defaultType: 'textfield',
      defaults: {
        width: 200,
        msgTarget: 'side'
      },
      items: [
        {
          fieldLabel: 'First Name',
          name: 'firstname',
          allowBlank: false,
          labelSeparator: ':'
        }, {
          fieldLabel: 'Last Name',
          name: 'lastname'
        }, {
          fieldLabel: 'Birthday',
          xtype: 'datefield'
        }, {
          fieldLabel: 'Street'
        }, {
          fieldLabel: 'Street Number',
          xtype: 'numberfield'
        }, {
          fieldLabel: 'Zipcode',
          xtype: 'numberfield'
        }, {
          fieldLabel: 'City'
        }, {
          fieldLabel: 'Allergies',
          xtype: 'textarea',
          name: 'allergies'
        }
      ],
      buttons: [
        {
          text: 'Save'
        }, {
          text: 'Cancel',
          handler: function(n) {}
        }
      ],
      hideLabels: false,
      labelAlign: 'left',
      labelPad: 8
    });
  }
  return AddChildForm;
})();