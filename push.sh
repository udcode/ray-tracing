setup_git() {
  git config --global user.email "travis@travis-ci.org"
  git config --global user.name "Travis CI"
}

commit_files() {
  git add .
  git commit --message "Travis build: $TRAVIS_BUILD_NUMBER"
  git checkout -B travis
  git merge master	
}

upload_files() {
  git remote add origin-build https://${GITHUB_API_KEY}@github.com/avisoftware/ray-tracing.git > /dev/null 2>&1
  git push --quiet --set-upstream origin-build travis
}

setup_git
commit_files
upload_files

