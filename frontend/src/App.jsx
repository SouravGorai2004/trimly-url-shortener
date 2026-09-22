import React, { useState } from 'react';
import { shortenUrl } from './services/api';
import './App.css';

// --- Bulletproof Inline SVG Icons ---
const GlobeIcon = () => (
    <svg width="18" height="18" viewBox="0 0 24 24" fill="none" stroke="currentColor" strokeWidth="2" strokeLinecap="round" strokeLinejoin="round">
        <circle cx="12" cy="12" r="10"></circle>
        <line x1="2" y1="12" x2="22" y2="12"></line>
        <path d="M12 2a15.3 15.3 0 0 1 4 10 15.3 15.3 0 0 1-4 10 15.3 15.3 0 0 1-4-10 15.3 15.3 0 0 1 4-10z"></path>
    </svg>
);

const LinkedinIcon = () => (
    <svg width="18" height="18" viewBox="0 0 24 24" fill="none" stroke="currentColor" strokeWidth="2" strokeLinecap="round" strokeLinejoin="round">
        <path d="M16 8a6 6 0 0 1 6 6v7h-4v-7a2 2 0 0 0-2-2 2 2 0 0 0-2 2v7h-4v-7a6 6 0 0 1 6-6z"></path>
        <rect x="2" y="9" width="4" height="12"></rect>
        <circle cx="4" cy="4" r="2"></circle>
    </svg>
);

const GithubIcon = () => (
    <svg width="18" height="18" viewBox="0 0 24 24" fill="none" stroke="currentColor" strokeWidth="2" strokeLinecap="round" strokeLinejoin="round">
        <path d="M9 19c-5 1.5-5-2.5-7-3m14 6v-3.87a3.37 3.37 0 0 0-.94-2.61c3.14-.35 6.44-1.54 6.44-7A5.44 5.44 0 0 0 20 4.77 5.07 5.07 0 0 0 19.91 1S18.73.65 16 2.48a13.38 13.38 0 0 0-7 0C6.27.65 5.09 1 5.09 1A5.07 5.07 0 0 0 5 4.77a5.44 5.44 0 0 0-1.5 3.78c0 5.42 3.3 6.61 6.44 7A3.37 3.37 0 0 0 9 18.13V22"></path>
    </svg>
);

function App() {
    const [url, setUrl] = useState('');
    const [shortUrl, setShortUrl] = useState('');
    const [error, setError] = useState('');
    const [copied, setCopied] = useState(false);
    const [loading, setLoading] = useState(false);

    const handleSubmit = async (e) => {
        e.preventDefault();
        setError('');
        setShortUrl('');
        setCopied(false);
        setLoading(true);

        try {
            const data = await shortenUrl(url);
            setShortUrl(data.shortUrl);
        } catch (err) {
            setError(err.message);
        } finally {
            setLoading(false);
        }
    };

    const handleCopy = () => {
        navigator.clipboard.writeText(shortUrl);
        setCopied(true);
        setTimeout(() => setCopied(false), 2000);
    };

    return (
        <div className="app-container">
            <header className="header">
                <h1>Trimly</h1>
                <p>Make Your Long URLs Shorter</p>
            </header>

            <main className="main-content">
                <div className="card">
                    <h2>Enter your long URL</h2>
                    <form onSubmit={handleSubmit} className="form-group">
                        <input
                            type="url"
                            placeholder="https://example.com/very-long-url"
                            value={url}
                            onChange={(e) => setUrl(e.target.value)}
                            required
                        />
                        <button type="submit" disabled={loading}>
                            {loading ? 'Shortening...' : 'Shorten URL'}
                        </button>
                    </form>

                    {error && <div className="error-message">{error}</div>}

                    {shortUrl && (
                        <div className="result-group">
                            <p>Your shortened URL</p>
                            <div className="short-url-box">
                                <a href={shortUrl} target="_blank" rel="noopener noreferrer">{shortUrl}</a>
                                <button onClick={handleCopy} className="copy-btn">
                                    {copied ? 'Copied!' : 'Copy'}
                                </button>
                            </div>
                        </div>
                    )}
                </div>
            </main>

            <footer className="footer">
                <p className="tech-stack">Built with Java + Spring Boot + React + PostgreSQL</p>

                <div className="social-links">
                    <a href="https://portfolio-frontend-w0uf.onrender.com/" target="_blank" rel="noopener noreferrer" className="social-link">
                        <GlobeIcon />
                        <span>Portfolio</span>
                    </a>
                    <a href="https://www.linkedin.com/in/souravgorai2004/" target="_blank" rel="noopener noreferrer" className="social-link">
                        <LinkedinIcon />
                        <span>LinkedIn</span>
                    </a>
                    <a href="https://github.com/SouravGorai2004" target="_blank" rel="noopener noreferrer" className="social-link">
                        <GithubIcon />
                        <span>GitHub</span>
                    </a>
                </div>

                <div className="footer-bottom">
                    <p>&copy; 2026 Sourav Gorai</p>
                    <p className="subtle-text">A simple backend-focused project</p>
                </div>
            </footer>
        </div>
    );
}

export default App;