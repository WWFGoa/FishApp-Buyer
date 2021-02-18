exports.handler = event => {
  //eslint-disable-line
  console.log(JSON.stringify(event, null, 2));
  event.Records.forEach(record => {
    console.log(record.eventID);
    console.log(record.eventName);
    console.log('DynamoDB Record: %j', record.dynamodb);

    if(record.eventName =="INSERT"){
        
    }
  });
  return Promise.resolve('Successfully processed DynamoDB record');
};
