/** @type {import('tailwindcss').Config} */
module.exports = {
  content: [],
  purge: ['./index.html', './src/**/*.{vue,js,ts,jsx,tsx}'],
  theme: {
    extend: {
      spacing: {
        10: '10px',
        12: '12px',
        14: '14px',
        16: '16px',
        18: '18px',
        20: '20px',
        24: '24px',
        30: '30px',
        40: '40px',
        50: '50px',
        60: '60px',
        '50%': '50%',
        '80%': '80%',
        '90%': '90%',
        '100%': '100%'
      }
    }
  },
  plugins: []
}
