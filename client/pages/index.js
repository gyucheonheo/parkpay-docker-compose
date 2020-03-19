import Layout from '../components/MyLayout';
import Landing from '../components/Landing';
import IconGrid from '../components/IconGrid';
import People from '../components/People';
import TechStack from '../components/TechStack';
const Index = props => (
  <Layout>
      <Landing />
      <IconGrid />
      <TechStack />
      <People />
  </Layout>
);



export default Index;