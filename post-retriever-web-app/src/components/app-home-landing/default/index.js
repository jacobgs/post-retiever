import React, { Fragment } from 'react'

/* form */
import Grid from '@material-ui/core/Grid'
import Content from '../page/content'
/* form */

const index = (props) => {
  return (
      <Grid container spacing={1} justify="center" alignItems="center">
        <div
          style={{
            width: '100%',
            margin: 'auto',
            paddingTop: 20,
            paddingBottom: 20,
            marginBottom: 50,
          }}
        >
          <div style={{ width: '90%', margin: 'auto' }}>
            <Content {...props} />
          </div>
        </div>
      </Grid>
  )
}

export default index
