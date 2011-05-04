ChildRecordBase = Ext.data.Record.create([
  {name: 'name', mapping: 'name'}
  {name: 'familyName', mapping: 'familyName'}
  {name: 'street', mapping: 'street'}
  {name: 'dateOfBirth', mapping: 'dateOfBirth', type: 'date', format: 'd.m.Y'}
  {name: 'allergies', mapping: 'allergies'}
  {name: 'town', mapping: 'town'}
  {name: 'zip', mapping: 'zip'}
])

class ChildRecord extends ChildRecordBase
  constructor: (data) ->
    super(
      name: data.name
      familyName: data.familyName
      street: data.adress.street + " " + data.adress.streetNumber
      dateOfBirth: new Date(data.dateOfBirth)
      allergies: data.allergies
      town: data.adress.town
      zip: data.adress.zip
    )