import React from 'react';

import Button from '@material-ui/core/Button';

/*
: properties
children
onClick
size
fullWidth
*/

const text = props => {
  return <Button {...props} color="primary" >
    { props.children }
  </Button>
}

export default text;
