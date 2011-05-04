var ChildRecord, ChildRecordBase;
var __hasProp = Object.prototype.hasOwnProperty, __extends = function(child, parent) {
  for (var key in parent) { if (__hasProp.call(parent, key)) child[key] = parent[key]; }
  function ctor() { this.constructor = child; }
  ctor.prototype = parent.prototype;
  child.prototype = new ctor;
  child.__super__ = parent.prototype;
  return child;
};
ChildRecordBase = Ext.data.Record.create([
  {
    name: 'name',
    mapping: 'name'
  }, {
    name: 'familyName',
    mapping: 'familyName'
  }, {
    name: 'street',
    mapping: 'street'
  }, {
    name: 'dateOfBirth',
    mapping: 'dateOfBirth',
    type: 'date',
    format: 'd.m.Y'
  }, {
    name: 'allergies',
    mapping: 'allergies'
  }, {
    name: 'town',
    mapping: 'town'
  }, {
    name: 'zip',
    mapping: 'zip'
  }
]);
ChildRecord = (function() {
  __extends(ChildRecord, ChildRecordBase);
  function ChildRecord(data) {
    ChildRecord.__super__.constructor.call(this, {
      name: data.name,
      familyName: data.familyName,
      street: data.adress.street + " " + data.adress.streetNumber,
      dateOfBirth: new Date(data.dateOfBirth),
      allergies: data.allergies,
      town: data.adress.town,
      zip: data.adress.zip
    });
  }
  return ChildRecord;
})();