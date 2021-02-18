/* Amplify Params - DO NOT EDIT
	API_FMP_GRAPHQLAPIENDPOINTOUTPUT
	API_FMP_GRAPHQLAPIIDOUTPUT
	AUTH_FMP_USERPOOLID
	ENV
	REGION
Amplify Params - DO NOT EDIT */exports.handler = event => {
  //eslint-disable-line
  console.log(JSON.stringify(event, null, 2));
  event.Records.forEach(record => {
    console.log(record.eventID);
    console.log(record.eventName);
    console.log('DynamoDB Record: %j', record.dynamodb);
  });
  return Promise.resolve('Successfully processed DynamoDB record');
};
