import React, { useContext, Fragment, useState, useEffect } from 'react'

/* package */
import AxiosJson from '../../axios-json'

import Table from '@material-ui/core/Table'
import TableBody from '@material-ui/core/TableBody'
import TableCell from '@material-ui/core/TableCell'
import TableContainer from '@material-ui/core/TableContainer'
import TableHead from '@material-ui/core/TableHead'
import TableRow from '@material-ui/core/TableRow'

import { makeStyles } from '@material-ui/core/styles'
import { Buttons, Inputs } from '../../lib-atomic'
/* package */

const useStyles = makeStyles((theme) => ({
  root: {
    '&:hover': {
      cursor: 'pointer',
    },
    height: 80,
  },
  table: {
    width: '100%',
  },
  assets: {
    height: '130px',
    overflow: 'hidden',
    paddingTop: theme.spacing(1),
    paddingBottom: theme.spacing(1),
  },
  upper: {
    textTransform: 'uppercase',
  },
  camel: {
    textTransform: 'capitalize',
  },
}))

const Index = (props) => {
  const classes = useStyles()
  const [submitted, setSubmitted] = useState(false)
  const [searchText, setSearchText] = useState('')
  const [searchText2, setSearchText2] = useState('')
  const [searchTextName, setSearchTextName] = useState('')
  const [rawContent, setRawContent] = useState([])
  const [warning, setWarning] = useState('')
  const [filteredContent, setFilteredContent] = useState([])

  const fetchListByPostId = async (id) => {
    const result = await AxiosJson({
      url: 'http://localhost:8080/posts/' + id + '?embed=comments',
      config: {
        method: 'GET',
        headers:{
          'Content-Type': 'application/json',
        },
      },
    })
    console.log(result);
    const data = result[1].json
    const parsed = {
      westpacPosts: [data.westpacPost],
      comments: data._embedded.comments,
    }
    setRawContent(parsed)
  }
  const fetchListByUserId = async (id) => {
    const result = await AxiosJson({
      url: 'http://localhost:8080/posts/searches?userId=' + id + '&embed=comments',
      config: {
        method: 'POST',
        headers:{
          'Content-Type': 'application/json',
        },
      },
    })
    console.log(result)
    const data = result[1].json
    console.log(data.westpacPosts)
    const parsed = {
      westpacPosts: data.westpacPosts,
      comments: data._embedded.comments,
    }
    setRawContent(parsed)
  }

  useEffect(() => {
    const filtered1 =
      rawContent &&
      rawContent.westpacPosts &&
      rawContent.westpacPosts.map((item) => {
        item.comments = rawContent.comments.filter(
          (comment) => comment.postId.toString() === item.postId.toString()
        )
        return item
      })
    if (filtered1 && filtered1.length > 0) {
      setFilteredContent(filtered1)
    } else {
      setFilteredContent([])
    }
  }, [rawContent])

  // input & button props
  const searchBarProps = {
    value: searchText,
    onChange: (e) => {
      setSearchText(e.target.value)
    },
  }
  const searchBarProps2 = {
    value: searchText2,
    onChange: (e) => {
      setSearchText2(e.target.value)
    },
  }
  const searchBtnProps = {
    fullWidth: true,
    style: { padding: 10, marginRight: 10, height: 35 },
    onClick: () => {
      console.log('Start Searching...')
      console.log('Text Typed: ', searchText)
      setSearchText2('')
      if (searchText.length === 0) {
        // fetchList()
        setWarning('Please provide texts')
      } else {
        setWarning('')
        fetchListByUserId(searchText)
      }
    },
  }
  const searchBtnProps2 = {
    fullWidth: true,
    style: { padding: 10, marginRight: 10, height: 35 },
    onClick: () => {
      console.log('Start Searching...')
      console.log('Text Typed: ', searchText2)
      setSearchText('')
      if (searchText2.length === 0) {
        // fetchList()
        setWarning('Please provide texts')
      } else {
        setWarning('')
        fetchListByPostId(searchText2)
      }
    },
  }

  return (
    <Fragment>
      <div style={{ display: 'flex', flexDirection: 'row' }}>
        <Inputs.Text {...searchBarProps} />

        <div
          style={{
            display: 'flex',
            flexDirection: 'row',
            width: '60%',
            margin: 'auto',
            marginLeft: 10,
          }}
        >
          <Buttons.Normal {...searchBtnProps}>Search By User ID</Buttons.Normal>
        </div>
      </div>
      <div style={{ display: 'flex', flexDirection: 'row' }}>
        <Inputs.Text {...searchBarProps2} />

        <div
          style={{
            display: 'flex',
            flexDirection: 'row',
            width: '60%',
            margin: 'auto',
            marginLeft: 10,
          }}
        >
          <Buttons.Normal {...searchBtnProps2}>
            Search By Post ID
          </Buttons.Normal>
        </div>
      </div>

      <div style={{ padding: 20, fontWeight: 800, color: 'red' }}>
        {warning.length !== 0 ? warning : ''}
      </div>
      <TableContainer>
        <Table
          className={classes.table}
          aria-labelledby="tableTitle"
          size={'small'}
          aria-label="enhanced table"
        >
          <TableHead>
            <TableRow>
              <TableCell>Post&nbsp;ID</TableCell>
              <TableCell>User&nbsp;ID</TableCell>
              <TableCell>Post Title</TableCell>
              <TableCell>Post Body</TableCell>
              <TableCell>Comments</TableCell>
            </TableRow>
          </TableHead>
          <TableBody>
            {filteredContent.length === 0 && (
             null
            )}
            {filteredContent &&
              filteredContent.length > 0 &&
              filteredContent.map((item) => {
                return (
                  <TableRow>
                    <TableCell style={{ verticalAlign: 'top' }}>
                      {item.postId}
                    </TableCell>
                    <TableCell style={{ verticalAlign: 'top' }}>
                      {item.userId}
                    </TableCell>
                    <TableCell style={{ verticalAlign: 'top' }}>
                      {item.postTitle}
                    </TableCell>
                    <TableCell style={{ verticalAlign: 'top' }}>
                      {item.postBody}
                    </TableCell>
                    <TableCell style={{ verticalAlign: 'top' }}>
                      {item.comments.map((comment) => {
                        return (
                          <TableRow>
                            <TableCell>
                              <span style={{ fontWeight: 900 }}>
                                {comment.userEmailAddress}:
                              </span>
                              <br />
                              {comment.commentName}
                              <br />
                              <br />
                              {comment.commentBody}
                              <br />
                            </TableCell>
                          </TableRow>
                        )
                      })}
                    </TableCell>
                  </TableRow>
                )
              })}
          </TableBody>
        </Table>
      </TableContainer>
    </Fragment>
  )
}

export default Index
