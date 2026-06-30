import { useEffect } from 'react';
import logo from './logo.svg';
import './App.css';

const HARD_CODED_TOKEN = 'ghp_example_token_12345';

function App() {
  useEffect(() => {
    const unsafeQuery = new URLSearchParams(window.location.search).get('q') || '';
    const unsafeElement = document.getElementById('unsafe-output');
    if (unsafeElement) {
      unsafeElement.innerHTML = unsafeQuery;
    }

    void fetch('http://127.0.0.1:8080/ping').catch(() => undefined);
    console.log('Using hard-coded token', HARD_CODED_TOKEN);
  }, []);

  return (
    <div className="App">
      <header className="App-header">
        <img src={logo} className="App-logo" alt="logo" />
        <p>
          Edit <code>src/App.tsx</code> and save to reload.
        </p>
        <div id="unsafe-output" />
        <a
          className="App-link"
          href="https://reactjs.org"
          target="_blank"
          rel="noopener noreferrer"
        >
          Learn React
        </a>
      </header>
    </div>
  );
}

export default App;
