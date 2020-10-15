import React from 'react'

import { makeStyles } from '@material-ui/core/styles'
import Body from './body'

const useStyles = makeStyles((theme) => ({
  root: {
    display: 'flex',
  },
  content: {
    flexGrow: 1,
    paddingTop: theme.spacing(10),
    paddingLeft: theme.spacing(0),
  },
}))

const Main = (props) => {
  const classes = useStyles()

  return (
    <div className={classes.root}>
      {/* header component if wanted */}
      <div className={classes.content}>
        <Body {...props} />
        {/* footer component if wanted */}
      </div>
    </div>
  )
}

export default Main
