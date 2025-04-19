module.exports = {
  extends: ['@commitlint/config-conventional'],
  rules: {
      'header-max-length': [2, 'always', 72],
      'subject-case': [2, 'always', 'sentence-case'],
      'subject-empty': [2, 'never'],
      'subject-full-stop': [2, 'never'],
      'type-enum': [
        2,
        'always',
        ['feat', 'fix', 'docs', 'style', 'refactor', 'perf', 'test', 'chore', 'ci', 'build'],
      ],
      'type-case': [2, 'always', 'lower-case'],
      'type-empty': [2, 'never'],
      'body-leading-blank': [2, 'always'],
      'body-max-line-length': [2, 'always', 72],
      'footer-leading-blank': [2, 'always'],
      'footer-max-line-length': [2, 'always', 72],
      'footer-empty': [2, 'never'],
      'breakline-before-body': [2, 'always'],
      'breaking-change': [2, 'always'],
    },
};