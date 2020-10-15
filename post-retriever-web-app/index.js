import React from "react";
import { Button, View, Text } from "react-native";
import { Ionicons } from '@expo/vector-icons';

import Layout from '../../layout';

import Nav from './nav';

class Home extends React.Component {
  static navigationOptions = Nav;
  render() {
    return <Layout.Container>
        <Layout.Header>
          <Text>example</Text>
        </Layout.Header>
        <Layout.Body>
          <Text>Body</Text>
        </Layout.Body>
      </Layout.Container>
  }
}

export default Home;
