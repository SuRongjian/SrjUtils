imap jj <ESC>
set nu
set hlsearch
set scrolloff=5

" vundle
set nocompatible              
filetype off                 
set rtp+=~/.vim/bundle/Vundle.vim
call vundle#begin()
Plugin 'VundleVim/Vundle.vim'
Plugin 'scrooloose/nerdtree'
call vundle#end()           
filetype plugin indent on  
