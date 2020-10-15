import React from "react";

import Button from "@material-ui/core/Button";

/*
: properties
children
size
fullWidth
*/

const normal = props => {
  return (
    <Button {...props} variant="contained" color="primary" type="submit">
      {props.children}
    </Button>
  );
};

export default normal;
