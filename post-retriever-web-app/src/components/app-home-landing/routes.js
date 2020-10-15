import React from 'react'
import { Route, Switch } from 'react-router-dom'
import CONFIG from './config'
import Default from './default'

const Routes = (props) => {
  const { prefix } = CONFIG.routes
  return <Route exact path={prefix} component={Default} />
}

export default Routes
