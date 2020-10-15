import React, { Fragment } from 'react'

import TextField from '@material-ui/core/TextField'

/*
: properties
name,
label,
value,
onChange,
helperText,
error
*/

const text = (props) => {
  const disabled = props.disabled ? true : false
  return (
    <Fragment>
      <TextField
        id="standard-full-width"
        {...props}
        disabled={disabled}
        fullWidth
        variant="outlined"
        margin="dense"
      />
    </Fragment>
  )
}

export default text
