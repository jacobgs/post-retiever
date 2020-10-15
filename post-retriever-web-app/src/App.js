import React, { Fragment } from 'react'
import { Route, Switch, Redirect } from 'react-router-dom'

import Main from './layout/main'

const App = () => {
  if (process.env.NODE_ENV === 'production')
    console.log = function no_console() {}
  return (
    <Fragment>
      <Switch>
        <Route exact path="/">
          <Redirect to="/home" component={Main} />
        </Route>
        <Route path="/:module" component={Main} />
      </Switch>
    </Fragment>
  )
}

export default App
