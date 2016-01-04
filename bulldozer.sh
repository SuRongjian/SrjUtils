#!/bin/bash

home="/home/rongjiansu"
repositoryParent="$home/e/workspace/space4/other"
repository="$repositoryParent/SrjUtils/"
branch="personalized"

vrapper=".vrapperrc"
vimperator=".vimperatorrc"
vim="_gvimrc"
this="bulldozer.sh"

vrapperFile="$home/$vrapper"
vimperatorFile="$home/$vimperator"
vimFile="$home/$vim"
thisFile="$home/softwareInstall/myBin/$this"

function initRepository() {
	if [ ! -d $repository ]; then 
		cd $repositoryParent
		git clone "git@github.com:SuRongjian/SrjUtils.git"
	fi
	cd $repository
	git checkout $branch
	git pull
	echo "已经切换到$branch分支" 
}

function backup() {
	initRepository
	cp $vrapperFile $repository
	cp $vimperatorFile $repository
	cp $vimFile $repository
	cp $thisFile $repository
	git add .
	git commit -m "提交点-`date`"
	git push origin $branch
	echo "已备份成功"
}


function restore() {
	initRepository
	cp $vrapper $vrapperFile
	cp $vimperator $vimperatorFile
	cp $vim $vimFile
	cp $this $thisFile
	echo "已恢复成功！"
}

if [ "$1" == "b" ]; then
	backup
elif [ "$1" == "r" ]; then 
	restore
fi

