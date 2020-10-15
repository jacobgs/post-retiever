import React from 'react'

/* Routes */
import HomeLanding from '../../components/app-home-landing'
/* Routes */

const importBody = (props) => {
  const pages = [HomeLanding]
  return pages.map((Page, i) => {
    return <Page.Routes {...props} key={i} />
  })
}
export default importBody
